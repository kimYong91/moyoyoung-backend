package org.community.moyoyoung.yangs01.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.community.moyoyoung.dto.*;
import org.community.moyoyoung.entity.Post;
import org.community.moyoyoung.repository.PostRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Log4j2
@RequiredArgsConstructor
public class PostListServiceImpl implements PostListService {

    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    @Override
    public PostDTO get(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found")); // 예외 처리
        return modelMapper.map(post, PostDTO.class);
    }

    @Override
    public PageResponseDTO<PostListDTO> list(PageRequestDTO pageRequestDTO) {
        PageRequest pageable = PageRequest.of(pageRequestDTO.getPage(), pageRequestDTO.getSize());

        Page<Post> result = postRepository.findAll(pageable);

        List<PostListDTO> dtoList = result.getContent().stream()
                .map(post -> modelMapper.map(post, PostListDTO.class))
                .collect(Collectors.toList());

        long totalCount = result.getTotalElements();

        PageResponseDTO<PostListDTO> responseDTO = PageResponseDTO.<PostListDTO>withAll()
                .dtoList(dtoList)
                .pageRequestDTO(pageRequestDTO)
                .totalCount(totalCount)
                .build();

            return responseDTO;
    }

}

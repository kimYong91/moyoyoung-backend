package org.community.moyoyoung.yangs01;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.community.moyoyoung.dto.*;
import org.community.moyoyoung.entity.Post;
import org.community.moyoyoung.repository.PostRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@Log4j2
@RequiredArgsConstructor
public class PostListServiceImpl implements PostListService {

    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    @Override
    public PostDTO getPostDetail(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found")); // 예외 처리
        return modelMapper.map(post, PostDTO.class);
    }

    @Override
    public PageResponseDTO<PostListDTO> getPostList(PageRequestDTO pageRequestDTO) {
        PageRequest pageable = PageRequest.of(pageRequestDTO.getPage(), pageRequestDTO.getSize());
        Page<Post> result = postRepository.findAll(pageable);

        List<PostListDTO> postList = result.getContent().stream()
                .map(post -> modelMapper.map(post, PostListDTO.class))
                .collect(Collectors.toList());

//        return PageResponseDTO.<PostListDTO>withAll(postList, pageRequestDTO, result.getTotalElements());
        return null;
    }

}

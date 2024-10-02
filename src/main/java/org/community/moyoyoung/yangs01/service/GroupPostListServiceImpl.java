package org.community.moyoyoung.yangs01.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.community.moyoyoung.dto.PageRequestDTO;
import org.community.moyoyoung.dto.PageResponseDTO;
import org.community.moyoyoung.dto.PostDTO;
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
public class GroupPostListServiceImpl implements GroupPostListService {

    private final ModelMapper modelMapper;

    private final PostRepository postRepository;

    @Override
    public Long register(PostDTO postDTO) {
        //Post 엔디디로 변환하고 저장한 후 Id 반환
        Post post = modelMapper.map(postDTO, Post.class);

        Post savedPost = postRepository.save(post);

        return savedPost.getId();
    }

    // 해당 아이디에 해당하는 게시글을 조회
    @Override
    public PostDTO getPostById(Long postId) {
        java.util.Optional<Post> result = postRepository.findById(postId);

        Post post = result.orElseThrow();

        PostDTO dto = modelMapper.map(post, PostDTO.class);

        return dto;
    }


    @Override
    public void modify(PostDTO postDTO) {
        Optional<Post> result = postRepository.findById(postDTO.getId());

        Post post = result.orElseThrow();

        post.setTitle(postDTO.getTitle());
        post.setPostImage(postDTO.getPostImage());
        post.setContent(postDTO.getContent());

        postRepository.save(post);
    }

    @Override
    public void remove(Long id) {

        postRepository.deleteById(id);
    }

    @Override
    public PageResponseDTO<PostDTO> list(PageRequestDTO pageRequestDTO) {

        Pageable pageable =
                PageRequest.of(
                        pageRequestDTO.getPage() - 1,  // 1페이지가 0이므로 주의
                        pageRequestDTO.getSize(),
                        Sort.by("tno").descending());

        Page<Post> result = postRepository.findAll(pageable);

        List<PostDTO> dtoList = result.getContent().stream()
                .map(todo -> modelMapper.map(todo, PostDTO.class))
                .collect(Collectors.toList());

        long totalCount = result.getTotalElements();

        PageResponseDTO<PostDTO> responseDTO = PageResponseDTO.<PostDTO>withAll()
                .dtoList(dtoList)
                .pageRequestDTO(pageRequestDTO)
                .totalCount(totalCount)
                .build();

        return responseDTO;
    }
}

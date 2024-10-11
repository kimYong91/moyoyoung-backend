package org.community.moyoyoung.yangs01.service;

import lombok.RequiredArgsConstructor;

import org.community.moyoyoung.dto.*;

import org.community.moyoyoung.entity.Post;
import org.community.moyoyoung.kimyong91.CustomFileUtil;
import org.community.moyoyoung.repository.PostRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService{

    private final ModelMapper modelMapper;
    private final PostRepository postRepository;
    private final CustomFileUtil customFileUtil;


    @Override
    public PostDTO get(Long id) {
        Optional<Post> result = postRepository.findById(id);
        Post post = result.orElseThrow();
        PostDTO postDTO = modelMapper.map(post, PostDTO.class);

        Long imageId = post.getPostImage().getId();
        customFileUtil.getImage(imageId);

        return postDTO;
    }

    @Override
    public PageResponseDTO<PostDTO> list(PageRequestDTO pageRequestDTO) {
        // 페이지 요청 정보 생성
        PageRequest pageable = PageRequest.of(pageRequestDTO.getPage(), pageRequestDTO.getSize());

        // 게시물 조회
        Page<Post> result = postRepository.findAll(pageable);

        // PostDTO 리스트로 변환
        List<PostDTO> dtoList = result.getContent().stream()
                .map(post -> modelMapper.map(post, PostDTO.class)) // Post 엔티티를 PostDTO로 변환
                .collect(Collectors.toList()); // 변환된 DTO를 리스트로 수집

        // PageResponseDTO<PostDTO> 생성 (빌더 패턴 사용)
        PageResponseDTO<PostDTO> responseDTO = PageResponseDTO.<PostDTO>withAll() // 빌더를 이용해 생성
                .dtoList(dtoList) // 변환된 PostDTO 리스트
                .pageRequestDTO(pageRequestDTO) // 클라이언트의 페이지 요청 정보
                .totalCount(result.getTotalElements()) // 총 게시물 수
                .prev(result.hasPrevious()) // 이전 페이지 존재 여부
                .next(result.hasNext()) // 다음 페이지 존재 여부
                .build(); // 최종적으로 빌드하여 객체 생성

        return responseDTO; // 변환된 응답 DTO 반환
    }

    @Override
    public List<PostDTO> getAllPosts() {
        List<Post> postList = postRepository.findAll(); // 엔티티 리스트 가져오기

        // 엔티티 리스트를 DTO 리스트로 변환
        return postList.stream()
                .map(post -> PostDTO.builder() // PostDTO 빌더 사용
                        .id(post.getId())
                        .title(post.getTitle())
                        .content(post.getContent())
                        .postImage(post.getPostImage()) // PostImage 변환
                        .commentList(post.getCommentList()) // 댓글 리스트 변환
//                        .myUser(post.getMyUser()) // MyUser 변환
                        .createDate(post.getCreateDate().toLocalDate()) // LocalDateTime에서 LocalDate로 변환
                        .build())
                .collect(Collectors.toList()); // 리스트로 수집
    }


//
//    @Override
//    public List<PostDTO> getAllPosts() {
//        // 게시글 리스트를 조회
//        List<Post> posts = postRepository.findAll();
//
//        // 게시글을 PostDTO로 변환하여 리스트에 담기
//        List<PostDTO> postDTOs = posts.stream()
//                .map(post -> modelMapper.map(post, PostDTO.class))
//                .collect(Collectors.toList());
//
//        return postDTOs; // 변환된 PostDTO 리스트 반환
//    }
}

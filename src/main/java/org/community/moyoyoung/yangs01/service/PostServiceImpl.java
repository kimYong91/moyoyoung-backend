//package org.community.moyoyoung.yangs01.service;
//
//import lombok.RequiredArgsConstructor;
//import org.community.moyoyoung.dto.PostDTO;
//import org.community.moyoyoung.entity.Post;
//import org.community.moyoyoung.repository.PostRepository;
//import org.modelmapper.ModelMapper;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//public class PostServiceImpl implements PostService{
//
//    private final ModelMapper modelMapper;
//
//    private final PostRepository postRepository;
//
////    @Override
////    public PostDTO get(Long id) {
////        java.util.Optional<Post> result = PostRepository.findById(id);
////
////        Post todo = result.orElseThrow(); //존재하지 않으면 기본 예외
////
////        PostDTO dto = modelMapper.map(todo, PostDTO.class);
////        return dto; // 변환된 DTO 반환
//    }
//}

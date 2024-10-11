package org.community.moyoyoung.yangs01.service;


import lombok.RequiredArgsConstructor;
import org.community.moyoyoung.entity.Post;
import org.community.moyoyoung.entity.PostImage;
import org.community.moyoyoung.repository.PostImageRepository;
import org.community.moyoyoung.repository.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PostImageService {
        private final PostImageRepository postImageRepository;
        private final PostRepository postRepository;


    public List<PostImage> saveImages(List<String> savedNames, List<MultipartFile> images, Long postId)  {
        List<PostImage> postImages = new ArrayList<>();

        // 게시글 ID를 사용하여 Post 객체 조회
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        for (int i = 0; i < savedNames.size(); i++) {
            String originalImageName = images.get(i).getOriginalFilename();
            String savedName = savedNames.get(i);

            PostImage postImage = PostImage.builder()
                    .fileName(savedName)
                    .upLoadFileName(originalImageName)
                    .mimeType(images.get(i).getContentType())
                    .createDate(LocalDateTime.now())
                    .post(post) // 이미지가 속하는 게시글 설정
                    .build();

            postImages.add(postImageRepository.save(postImage)); // 이미지 저장
        }

        return postImages; // 저장된 이미지 리스트 반환
    }
}


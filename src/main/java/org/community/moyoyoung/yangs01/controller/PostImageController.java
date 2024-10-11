package org.community.moyoyoung.yangs01.controller;

import lombok.RequiredArgsConstructor;
import org.community.moyoyoung.dto.PostImageDTO;
import org.community.moyoyoung.entity.Post;
import org.community.moyoyoung.entity.PostImage;
import org.community.moyoyoung.kimyong91.CustomFileUtil;
import org.community.moyoyoung.repository.PostRepository;
import org.community.moyoyoung.yangs01.service.PostImageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("")
public class PostImageController {

    private final PostImageService postImageService;
    private final PostRepository postRepository;
    private final CustomFileUtil customFileUtil;


    @PostMapping("/posts/{postId}/images")
    public ResponseEntity<List<PostImage>> uploadImage(@PathVariable Long postId, @RequestParam("image") List<MultipartFile> images) throws IOException {
        try {
            List<String> savedNames = customFileUtil.saveFile(images); // 파일 저장 및 저장된 이름 리스트 반환
            List<PostImage> savedImages = postImageService.saveImages(savedNames, images, postId); // 서비스에 저장된 이름과 파일 전달

            return ResponseEntity.ok(savedImages); // PostImage 리스트 반환
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null); // 잘못된 파일 형식 처리
        }
    }

}


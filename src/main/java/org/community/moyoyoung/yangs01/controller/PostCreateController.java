package org.community.moyoyoung.yangs01.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.community.moyoyoung.dto.PostCreateDTO;
import org.community.moyoyoung.yangs01.service.PostCreateService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Log4j2
@RestController
@RequestMapping("/api/allBoard")
public class PostCreateController {

    private final PostCreateService postCreateService;

//    @PostMapping
//    public PostDTO createPost(@RequestBody PostDTO postDTO) {
//        PostDTO createdPost = postCreateService.create(postDTO); // 게시글 작성 서비스 호출
//        return createdPost; // 작성된 게시글 반환
//    }
    @PostMapping("/posts/create")
    public ResponseEntity<String> createPost(@RequestParam("title") String title,
                                             @RequestParam("content") String content,
                                             @RequestParam("myUserId") Long myUserId,
                                             @RequestParam("files") List<MultipartFile> files) {
        PostCreateDTO postCreateDTO = PostCreateDTO.builder()
                .title(title)
                .content(content)
                .myUserId(myUserId)
                .createDate(LocalDateTime.now())
                .files(files)
                .build();

        Long postId = postCreateService.register(postCreateDTO);
        return new ResponseEntity<>("Post created with ID: " + postId, HttpStatus.CREATED);
    }

    @PutMapping("/posts/modify/{id}")
    public ResponseEntity<String> modifyPost(@PathVariable Long id,
                                             @RequestParam("title") String title,
                                             @RequestParam("content") String content,
                                             @RequestParam("files") List<MultipartFile> files) {
        PostCreateDTO postCreateDTO = PostCreateDTO.builder()
                .title(title)
                .content(content)
                .files(files)
                .build();

        postCreateService.modify(id, postCreateDTO);
        return new ResponseEntity<>("Post modified with ID: " + id, HttpStatus.OK);
    }
}

package org.community.moyoyoung.yangs01.controller;

import org.community.moyoyoung.dto.PostCreateDTO;
import org.community.moyoyoung.yangs01.PostCreateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostCreateController {
    private final PostCreateService postCreateService;

    @Autowired
    public PostCreateController(PostCreateService postCreateService) {
        this.postCreateService = postCreateService;
    }

    @PostMapping("/create")
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

    @PutMapping("/modify/{id}")
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

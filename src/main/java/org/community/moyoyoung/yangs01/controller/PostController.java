package org.community.moyoyoung.yangs01.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.community.moyoyoung.dto.PostDTO;
import org.community.moyoyoung.yangs01.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Log4j2
@RestController
@RequestMapping("/api/allBoard")
public class PostController {

    private final PostService postService;

    @GetMapping("/posts/{id}")
    public ResponseEntity<PostDTO> get(@PathVariable(name = "id") Long id) {
        System.out.println("--------------------------------------포스트 깐뜨롤러");
        try {
            PostDTO postDTO = postService.get(id);
            return ResponseEntity.ok(postDTO);
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }


//    @GetMapping("/list")
//    public PageResponseDTO<PostListDTO> list(PageRequestDTO pageRequestDTO ) {
//
//        log.info(pageRequestDTO);
//
//        return service.list(pageRequestDTO);
//    }

    @GetMapping("/posts/list/{group_id}")
    public List<PostDTO> list(@PathVariable(name = "group_id") Long group_id ){
        List<PostDTO> postDTOList = postService.getAllPosts(group_id);
        System.out.println("--------------------------------------깐뜨롤러");
        return postDTOList;
    }



}




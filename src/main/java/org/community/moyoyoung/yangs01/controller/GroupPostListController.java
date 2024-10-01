package org.community.moyoyoung.yangs01.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.community.moyoyoung.dto.PageRequestDTO;
import org.community.moyoyoung.dto.PageResponseDTO;
import org.community.moyoyoung.dto.PostDTO;
import org.community.moyoyoung.yangs01.service.GroupPostListService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequiredArgsConstructor
@Log4j2
@RestController
@RequestMapping("")
public class GroupPostListController {

    private final GroupPostListService service;

    @GetMapping("/{id}")
    public PostDTO get(@PathVariable(name = "id") Long id) {
        return service.get(id);
    }

    @GetMapping("/list")
    public PageResponseDTO<PostDTO> list(PageRequestDTO pageRequestDTO ) {

        log.info(pageRequestDTO);

        return service.list(pageRequestDTO);
    }

    // 객체를 받아서 게시글 등록, 등록된 id값 반환
    @PostMapping("/")
    public Map<String, Long> register(@RequestBody PostDTO postDTO){

        log.info("PostDTO: " + postDTO);

        Long id = service.register(postDTO);

        return Map.of("ID", id);
    }
}

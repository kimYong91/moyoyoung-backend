package org.community.moyoyoung.yangs01.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.community.moyoyoung.dto.PageRequestDTO;
import org.community.moyoyoung.dto.PageResponseDTO;
import org.community.moyoyoung.dto.PostListDTO;
import org.community.moyoyoung.yangs01.service.PostListService;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Log4j2
@RestController
@RequestMapping("/api/allBoard")
public class PostListController {

    private final PostListService service;

    @GetMapping("/{id}")
    public PostListDTO get(@PathVariable(name = "id") Long id) {
        return service.get(id);
    }

    @GetMapping("/list")
    public PageResponseDTO<PostListDTO> list(PageRequestDTO pageRequestDTO ) {

        log.info(pageRequestDTO);

        return service.list(pageRequestDTO);
    }

}




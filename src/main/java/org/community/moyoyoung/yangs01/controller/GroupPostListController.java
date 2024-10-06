package org.community.moyoyoung.yangs01.controller;

import ch.qos.logback.core.model.Model;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.community.moyoyoung.dto.PageRequestDTO;
import org.community.moyoyoung.dto.PageResponseDTO;
import org.community.moyoyoung.dto.PostDTO;
import org.community.moyoyoung.yangs01.GroupPostListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequiredArgsConstructor
@Log4j2
@RestController
@RequestMapping("")
public class GroupPostListController {

    private final GroupPostListService service;

    @GetMapping("/{id}")
    public PostDTO get(@PathVariable(name = "id") Long postId) {
        return service.getPostById(postId);
    }

    @GetMapping("/list")
    public PageResponseDTO<PostDTO> list(PageRequestDTO pageRequestDTO ) {

        log.info(pageRequestDTO);

        return service.list(pageRequestDTO);
    }

    @GetMapping("/list")
    public PageResponseDTO<PostDTO> list(PageRequestDTO pageRequestDTO, @RequestParam("status") String status) {

        PageResponseDTO<PostDTO> pageResponseDTO = service.list(pageRequestDTO);

        // 각 게시글에 대해 작성자 정보를 상태에 맞게 설정
        pageResponseDTO.getDtoList().forEach(postDTO -> {
            String authorDisplayName;
            if ("online".equals(status)) {
                authorDisplayName = postDTO.getNickName(); // 온라인 상태에서는 닉네임
            } else {
                authorDisplayName = postDTO.getName(); // 오프라인 상태에서는 이름
            }

            // 작성자 정보를 name 필드에 설정 (혹은 새로운 필드를 사용할 수 있음)
            postDTO.setName(authorDisplayName);
        });

        return pageResponseDTO;
    }

//    // 객체를 받아서 게시글 등록, 등록된 id값 반환 (이거는 작성화면 컨트롤러에 작성)
//    @PostMapping("/")
//    public Map<String, Long> register(@RequestBody PostDTO postDTO){
//
//        log.info("PostDTO: " + postDTO);
//
//        Long id = service.register(postDTO);
//
//        return Map.of("ID", id);
//    }

    // 게시글 상세 페이지 조회
//    @GetMapping("/posts/{id}")
//    public String getPostDetail(@PathVariable Long id, Model model) {
//        PostDTO postDTO = groupPostListService.getPostById(id); // 서비스 호출
//        model.addText("post", postDTO); // 모델에 게시글 정보 추가
//        return "postDetail"; // 게시글 상세 페이지 뷰 이름 반환
//    }
}

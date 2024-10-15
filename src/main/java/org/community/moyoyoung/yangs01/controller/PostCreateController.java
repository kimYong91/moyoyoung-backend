package org.community.moyoyoung.yangs01.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.community.moyoyoung.dto.MyUserDTO;
import org.community.moyoyoung.dto.PostCreateDTO;
import org.community.moyoyoung.entity.MyUser;
import org.community.moyoyoung.kimyong91.CustomFileUtil;
import org.community.moyoyoung.samgak0.services.AuthService;
import org.community.moyoyoung.yangs01.service.PostCreateService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Log4j2
@RestController
@RequestMapping("/api/allBoard")
public class PostCreateController {

    private final PostCreateService postCreateService;
    private final CustomFileUtil customFileUtil;
    private final ModelMapper modelMapper;
    private final AuthService authService;


    @PostMapping("/posts/create")
    public ResponseEntity<Map<String ,Long>> createPost( @ModelAttribute PostCreateDTO postCreateDTO) {
        // 파일 처리
        List<MultipartFile> files = postCreateDTO.getFiles();
        List<String> uploadFileName = customFileUtil.saveFile(files);
        postCreateDTO.setUploadFileName(uploadFileName);

        // 현재 로그인한 사용자 정보 가져오기
        MyUser myUser = myUser = authService.getLoginData().orElseThrow();

        // 게시글 등록
        Long id = postCreateService.register(postCreateDTO, myUser);
        return ResponseEntity.ok(Map.of("result", id));
    }


//@PostMapping("/posts/create")
//public ResponseEntity<Map<String, Long>> createPost(@RequestParam("title") String title,
//                                                    @RequestParam("content") String content,
//                                                    @RequestParam("groupId") Long groupId, // 그룹 ID를 파라미터로 받음
//                                                    @RequestParam("files") List<MultipartFile>  files) {
//    System.out.println("Group ID: " + groupId);
//
//
//    // PostCreateDTO 객체 생성
//    PostCreateDTO postCreateDTO = PostCreateDTO.builder()
//            .title(title)
//            .content(content)
//            .groupId(groupId)
//            .files(files)
//            .build();
//
//    // 현재 로그인한 사용자 정보 가져오기
//    Optional<MyUserDTO> myUserDTO = authService.getLoginData();
//    MyUser myUser = modelMapper.map(myUserDTO, MyUser.class);
//
//    // 게시글 등록
//    Long postId = postCreateService.register(postCreateDTO, myUser);
//
//    // 응답 반환
//    return ResponseEntity.ok(Map.of("result", postId));
//
//}


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

    @DeleteMapping("/{id}")
    public Map<String, String> remove(@PathVariable(name="id") Long id ){

        log.info("Remove:  " + id);

        postCreateService.remove(id);

        return Map.of("RESULT", "SUCCESS");
    }
}

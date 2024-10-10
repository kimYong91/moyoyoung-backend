package org.community.moyoyoung.kimyong91.controller;

import lombok.RequiredArgsConstructor;
import org.community.moyoyoung.dto.*;
import org.community.moyoyoung.entity.MyUser;
import org.community.moyoyoung.kimyong91.CustomFileUtil;
import org.community.moyoyoung.kimyong91.service.GroupService;
import org.community.moyoyoung.samgak0.services.AuthService;
import org.community.moyoyoung.samgak0.services.MyUserService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.Optional;

// 김용
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/group")
public class GroupController {

    private final GroupService groupService;
    private final CustomFileUtil customFileUtil;
    private final MyUserService myUserService;
    private final ModelMapper modelMapper;
    private final AuthService authService;


    @GetMapping("/{id}")
    public ResponseEntity<GroupDetailDTO> getGroupDetails(@PathVariable(name = "id") Long id) {
        GroupDetailDTO groupDetail = groupService.getGroupDetail(id);
        return ResponseEntity.ok(groupDetail);
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, Long>> groupRegister(GroupDTO groupDTO, Authentication authentication) {
        List<MultipartFile> files = groupDTO.getFile();

        List<String> uploadFileName = customFileUtil.saveFile(files);
        groupDTO.setUploadFileName(uploadFileName);


        Optional<MyUserDTO> myUserDTO = authService.getLoginData();
        MyUser myUser = modelMapper.map(myUserDTO, MyUser.class);


        Long id = groupService.register(groupDTO, myUser);
        return ResponseEntity.ok(Map.of("result", id));
    }

//    @PostMapping("/join/{groupId}/{userId}")
//    public ResponseEntity<Map<String, String>> groupJoin(@PathVariable(name = "groupId") Long groupId, @PathVariable(name = "userId") Long userId) {
//        groupService.groupJoin(groupId, userId);
//        return ResponseEntity.ok(Map.of("result", "SUCCESS"));
//    }


    @PutMapping("/{id}")
    public ResponseEntity<Map<String, String>> modify(@PathVariable(name = "id") Long id,
                                                      @RequestBody GroupDTO groupDTO) {
        groupDTO.setId(id);
        groupService.modify(groupDTO);
        return ResponseEntity.ok(Map.of("result", "SUCCESS"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> remove(@PathVariable(name = "id") Long id) {
        groupService.remove(id);
        return ResponseEntity.ok(Map.of("result", "SUCCESS"));
    }

}

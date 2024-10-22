package org.community.moyoyoung.kimyong91.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.community.moyoyoung.dto.*;
import org.community.moyoyoung.entity.MyUser;
import org.community.moyoyoung.kimyong91.service.GroupService;
import org.community.moyoyoung.kimyong91.service.GroupUserService;
import org.community.moyoyoung.samgak0.services.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

// 김용
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/group")
public class GroupController {

    private final GroupService groupService;
    private final AuthService authService;
    private final GroupUserService groupUserService;


    @GetMapping("/detail/{groupId}")
    public ResponseEntity<GroupDetailDTO> getGroupDetails(@PathVariable(name = "groupId") Long groupId) {
        GroupDetailDTO groupDetail = groupService.getGroupDetail(groupId);
        return ResponseEntity.ok(groupDetail);
    }

    @GetMapping("/state/{groupId}")
    public ResponseEntity<userStateDTO> getGroupUserState(@PathVariable(name = "groupId") Long groupId){
        MyUser myUser = null;
        try {
            myUser = authService.getLoginData().orElseThrow();
        } catch(Exception e) {
            myUser = new MyUser();
        }
        userStateDTO groupUserState = groupService.getGroupUserState(groupId, myUser.getId());
        return ResponseEntity.ok(groupUserState);
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, Long>> groupRegister(GroupDTO groupDTO) {


        Long id = groupService.register(groupDTO);
        return ResponseEntity.ok(Map.of("result", id));
    }

    @PostMapping("/join/{groupId}")
    public ResponseEntity<Map<String, String>> groupJoin(@PathVariable(name = "groupId") Long groupId) {

        groupUserService.groupJoin(groupId);

        return ResponseEntity.ok(Map.of("result", "SUCCESS"));
    }


    // modify/      추가
    @PutMapping("/modify/{groupId}")
    public ResponseEntity<Map<String, String>> groupModify(@PathVariable(name = "groupId") Long groupId,
                                                      @RequestBody GroupDTO groupDTO) {
        groupDTO.setId(groupId);
        groupService.modify(groupDTO);

        return ResponseEntity.ok(Map.of("result", "SUCCESS"));
    }
    // remove/      추가
    @DeleteMapping("/remove/{groupId}")
    public ResponseEntity<Map<String, String>> groupUpdateRemove(@PathVariable(name = "groupId") Long groupId) {

        groupService.updateToRemove(groupId);

        return ResponseEntity.ok(Map.of("result", "SUCCESS"));
    }

    @DeleteMapping("/realRemove/{groupId}")
    public ResponseEntity<Map<String, String>> groupRealRemove(@PathVariable(name = "groupId") Long groupId) {

        groupService.realRemove(groupId);

        return ResponseEntity.ok(Map.of("result", "SUCCESS"));
    }
}

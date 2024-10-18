package org.community.moyoyoung.kimyong91.controller;

import lombok.RequiredArgsConstructor;
import org.community.moyoyoung.dto.GroupUserDTO;
import org.community.moyoyoung.kimyong91.service.GroupUserService;
import org.community.moyoyoung.samgak0.services.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/groupUser")
public class GroupUserController {

    private final GroupUserService groupUserService;
    private final AuthService authService;

    @GetMapping("/list")
    public ResponseEntity<List<GroupUserDTO>> getJoinUserList() {
        List<GroupUserDTO> listAll = groupUserService.getGroupUserListAll();
        return ResponseEntity.ok(listAll);
    }

    @PostMapping("/join/{groupId}")
    public ResponseEntity<Map<String, String>> meetingJoin(@PathVariable(name = "groupId") Long groupId) {
        groupUserService.groupJoin(groupId);
        return ResponseEntity.ok(Map.of("result", "SUCCESS"));
    }

    @PostMapping("/change/{newOwnUserId}/{groupId}")
    public ResponseEntity<Map<String, String>> groupOwnUserChange(@PathVariable(name = "newOwnUserId") Long newOwnUserId, @PathVariable(name = "groupId") Long groupId) {
        groupUserService.groupOwnUserChange(newOwnUserId, groupId);
        return ResponseEntity.ok(Map.of("result", "SUCCESS"));
    }


    @DeleteMapping("/secession") // 그룹 탈퇴
    public ResponseEntity<Map<String, String>> groupUserSecession() {
        groupUserService.groupUserSecession();
        return ResponseEntity.ok(Map.of("result", "SUCCESS"));
    }

}

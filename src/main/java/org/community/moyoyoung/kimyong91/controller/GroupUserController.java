package org.community.moyoyoung.kimyong91.controller;

import lombok.RequiredArgsConstructor;
import org.community.moyoyoung.dto.GroupUserDTO;
import org.community.moyoyoung.kimyong91.service.GroupUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/groupUser")
public class GroupUserController {

    private final GroupUserService groupUserService;

    @GetMapping("/list")
    public ResponseEntity<List<GroupUserDTO>> getJoinUserList() {
        List<GroupUserDTO> listAll = groupUserService.getGroupUserListAll();
        return ResponseEntity.ok(listAll);
    }

    @PostMapping("/join/{groupId}/{userId}")
    public ResponseEntity<Map<String, String>> meetingJoin(@PathVariable(name = "groupId") Long groupId, @PathVariable(name = "userId") Long userId) {
        groupUserService.groupJoin(groupId, userId);
        return ResponseEntity.ok(Map.of("result", "SUCCESS"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> meetingUserRemove(@PathVariable(name = "id") Long id) {
        groupUserService.groupUserRemove(id);
        return ResponseEntity.ok(Map.of("result", "SUCCESS"));
    }
}

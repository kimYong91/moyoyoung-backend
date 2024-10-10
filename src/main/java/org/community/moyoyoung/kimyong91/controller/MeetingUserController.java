package org.community.moyoyoung.kimyong91.controller;

import lombok.RequiredArgsConstructor;
import org.community.moyoyoung.dto.MeetingUserDTO;
import org.community.moyoyoung.entity.MeetingUser;
import org.community.moyoyoung.kimyong91.service.MeetingUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
// 김용
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/meetingUser")
public class MeetingUserController {

    private final MeetingUserService meetingUserService;

    @GetMapping("/list")
    public ResponseEntity<List<MeetingUserDTO>> getJoinUserList() {
        List<MeetingUserDTO> listAll = meetingUserService.getListAll();
        return ResponseEntity.ok(listAll);
    }

    @PostMapping("/join/{id}/{userId}")
    public ResponseEntity<Map<String, MeetingUser>> meetingJoin(@PathVariable(name = "id") Long id, @PathVariable(name = "id") Long userId) {
        MeetingUser meetingUser = meetingUserService.join(id, userId);
        return ResponseEntity.ok(Map.of("id", meetingUser));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> meetingUserRemove(@PathVariable(name = "id") Long id) {
        meetingUserService.remove(id);
        return ResponseEntity.ok(Map.of("result", "SUCCESS"));
    }

}

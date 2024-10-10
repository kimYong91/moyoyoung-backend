package org.community.moyoyoung.kimyong91.controller;

import lombok.RequiredArgsConstructor;
import org.community.moyoyoung.dto.MeetingUserDTO;
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
        List<MeetingUserDTO> listAll = meetingUserService.getMeetingUserListAll();
        return ResponseEntity.ok(listAll);
    }

    @PostMapping("/join/{meetingId}/{userId}")
    public ResponseEntity<Map<String, String>> meetingJoin(@PathVariable(name = "meetingId") Long meetingId, @PathVariable(name = "userId") Long userId) {
        meetingUserService.meetingJoin(meetingId, userId);
        return ResponseEntity.ok(Map.of("result", "SUCCESS"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> meetingUserRemove(@PathVariable(name = "id") Long id) {
        meetingUserService.meetingUserRemove(id);
        return ResponseEntity.ok(Map.of("result", "SUCCESS"));
    }

}

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
        List<MeetingUserDTO> listAll = meetingUserService.getListAll();
        return ResponseEntity.ok(listAll);
    }

    @PostMapping("/join")
    public ResponseEntity<Map<String, Long>> meetingJoin(@RequestBody MeetingUserDTO meetingUserDTO) {
        Long id = meetingUserService.join(meetingUserDTO);
        return ResponseEntity.ok(Map.of("id", id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> meetingUserRemove(@PathVariable(name = "id") Long id) {
        meetingUserService.remove(id);
        return ResponseEntity.ok(Map.of("result", "SUCCESS"));
    }

}

package org.community.moyoyoung.kimyong91.controller;

import lombok.RequiredArgsConstructor;
import org.community.moyoyoung.dto.MeetingDTO;
import org.community.moyoyoung.kimyong91.service.MeetingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
// 김용
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/meeting")
public class MeetingController {

    private final MeetingService meetingService;

    @PostMapping("/register")
    public ResponseEntity<Map<String, Long>> meetingRegister(@RequestBody MeetingDTO meetingDTO) {
        System.out.println(meetingDTO);
        Long meetingId = meetingService.register(meetingDTO);
        System.out.println(meetingDTO);
        return ResponseEntity.ok(Map.of("meetingId", meetingId));
    }

    @PutMapping("/{meetingId}")
    public ResponseEntity<Map<String, String>> meetingModify(@PathVariable(name = "meetingId") Long meetingId,
                                                      @RequestBody MeetingDTO meetingDTO) {
        meetingDTO.setId(meetingId);
        meetingService.modify(meetingDTO);

        return ResponseEntity.ok(Map.of("result", "SUCCESS"));
    }

    @DeleteMapping("/{meetingId}")
    public ResponseEntity<Map<String, String>> meetingRemove(@PathVariable(name = "meetingId") Long meetingId) {
        meetingService.remove(meetingId);
        return ResponseEntity.ok(Map.of("result", "SUCCESS"));
    }
}

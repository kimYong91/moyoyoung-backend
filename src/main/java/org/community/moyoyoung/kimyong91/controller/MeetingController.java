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
        Long id = meetingService.register(meetingDTO);
        return ResponseEntity.ok(Map.of("id", id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, String>> meetingModify(@PathVariable(name = "id") Long id,
                                                      @RequestParam MeetingDTO meetingDTO) {
        meetingDTO.setId(id);
        meetingService.modify(meetingDTO);
        return ResponseEntity.ok(Map.of("result", "SUCCESS"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> meetingRemove(@PathVariable(name = "id") Long id) {
        meetingService.remove(id);
        return ResponseEntity.ok(Map.of("result", "SUCCESS"));
    }
}

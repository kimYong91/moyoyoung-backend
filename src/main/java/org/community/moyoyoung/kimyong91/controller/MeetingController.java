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
@RequestMapping("/test/meeting")
public class MeetingController {

    private final MeetingService meetingService;

    @GetMapping("/{id}")
    public ResponseEntity<MeetingDTO> get(@PathVariable(name = "id") Long id) {
        MeetingDTO meetingDTO = meetingService.get(id);
        return ResponseEntity.ok(meetingDTO);
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, Long>> register(@RequestBody MeetingDTO meetingDTO) {
        Long id = meetingService.register(meetingDTO);
        return ResponseEntity.ok(Map.of("id", id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, String>> modify(@PathVariable(name = "id") Long id,
                                                      @RequestParam MeetingDTO meetingDTO) {
        meetingDTO.setId(id);
        meetingService.modify(meetingDTO);
        return ResponseEntity.ok(Map.of("result", "SUCCESS"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> remove(@PathVariable(name = "id") Long id) {
        meetingService.remove(id);
        return ResponseEntity.ok(Map.of("result", "SUCCESS"));
    }
}

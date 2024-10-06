package org.community.moyoyoung.kimyong91.controller;

import lombok.RequiredArgsConstructor;
import org.community.moyoyoung.dto.MeetingUserDTO;
import org.community.moyoyoung.kimyong91.service.MeetingUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping()
public class MeetingUserController {

    private final MeetingUserService meetingUserService;

    @GetMapping("/list")
    public ResponseEntity<List<MeetingUserDTO>> getList() {
        List<MeetingUserDTO> listAll = meetingUserService.getListAll();
        return ResponseEntity.ok(listAll);
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, Long>> register(@RequestBody MeetingUserDTO meetingUserDTO) {
        Long id = meetingUserService.register(meetingUserDTO);
        return ResponseEntity.ok(Map.of("id", id));
    }

    @DeleteMapping("/{id]")
    public ResponseEntity<Map<String, String>> remove(@PathVariable(name = "id") Long id) {
        meetingUserService.remove(id);
        return ResponseEntity.ok(Map.of("result", "SUCCESS"));
    }

}

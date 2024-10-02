package org.community.moyoyoung.kimyong91.controller;

import lombok.RequiredArgsConstructor;
import org.community.moyoyoung.dto.*;
import org.community.moyoyoung.kimyong91.service.GroupService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("")
public class GroupController {

    private final GroupService groupService;

    @GetMapping("/{id}")
    public ResponseEntity<GroupDTO> getOne(@PathVariable(name = "id") Long id) {
        GroupDTO response = groupService.getOne(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("")
    public ResponseEntity<PageResponseDTO<PostMiniDTO>> getPostMiniList(
            @RequestParam GroupDTO groupDTO,
            @RequestParam int page,
            @RequestParam int size) {

        PageRequestDTO pageRequestDTO = new PageRequestDTO(page, size);
        PageResponseDTO<PostMiniDTO> response = groupService.getPostMiniList(groupDTO, pageRequestDTO);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MeetingDTO> getMeeting(@PathVariable(name = "id") Long id) {
        MeetingDTO response = groupService.getMeeting(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/")
    public ResponseEntity<Map<String, Long>> register(@RequestBody GroupDTO groupDTO) {
        Long id = groupService.register(groupDTO);
        return ResponseEntity.ok(Map.of("id", id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, String>> modify(@PathVariable(name = "id") Long id,
                                    @RequestParam GroupDTO groupDTO) {
        groupDTO.setId(id);
        groupService.modify(groupDTO);
        return ResponseEntity.ok(Map.of("result", "SUCCESS"));
    }

    @DeleteMapping("/{id]")
    public ResponseEntity<Map<String, String>> remove(@PathVariable(name = "id") Long id) {
        groupService.remove(id);
        return ResponseEntity.ok(Map.of("result", "SUCCESS"));
    }

}

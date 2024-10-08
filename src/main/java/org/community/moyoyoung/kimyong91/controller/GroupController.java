package org.community.moyoyoung.kimyong91.controller;

import lombok.RequiredArgsConstructor;
import org.community.moyoyoung.dto.*;
import org.community.moyoyoung.kimyong91.CustomFileUtil;
import org.community.moyoyoung.kimyong91.service.GroupService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

// 김용
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/group")
public class GroupController {

    private final GroupService groupService;
    private final CustomFileUtil customFileUtil;

    @GetMapping("/{id}")
    public ResponseEntity<GroupDTO> getOneGroup(@PathVariable(name = "id") Long id) {
        GroupDTO response = groupService.getOne(id);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/postList")
    public ResponseEntity<List<PostMiniDTO>> getPostMiniList(@RequestParam Long id) {
        List<PostMiniDTO> postMiniList1 = groupService.getPostMiniList(id);
        return ResponseEntity.ok(postMiniList1);
    }


    @GetMapping("/meeting/{id}")
    public ResponseEntity<MeetingDTO> getMeeting(@PathVariable(name = "id") Long id) {
        MeetingDTO response = groupService.getMeeting(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, Long>> groupRegister(GroupDTO groupDTO) {
        List<MultipartFile> files = groupDTO.getFile();

        List<String> uploadFileName = customFileUtil.saveFile(files);
        groupDTO.setUploadFileName(uploadFileName);
        Long id = groupService.register(groupDTO);
        return ResponseEntity.ok(Map.of("result", id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, String>> modify(@PathVariable(name = "id") Long id,
                                    @RequestBody GroupDTO groupDTO) {
        groupDTO.setId(id);
        groupService.modify(groupDTO);
        return ResponseEntity.ok(Map.of("result", "SUCCESS"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> remove(@PathVariable(name = "id") Long id) {
        groupService.remove(id);
        return ResponseEntity.ok(Map.of("result", "SUCCESS"));
    }

}

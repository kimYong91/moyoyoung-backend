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
    public ResponseEntity<GroupDetailDTO> getGroupDetails(@PathVariable(name = "id") Long id) {
        GroupDetailDTO groupDetail = groupService.getGroupDetail(id);
        return ResponseEntity.ok(groupDetail);
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

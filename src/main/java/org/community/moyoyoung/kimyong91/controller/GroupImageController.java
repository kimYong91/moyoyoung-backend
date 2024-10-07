package org.community.moyoyoung.kimyong91.controller;

import lombok.RequiredArgsConstructor;
import org.community.moyoyoung.entity.GroupImage;
import org.community.moyoyoung.kimyong91.CustomFileUtil;
import org.community.moyoyoung.kimyong91.service.GroupImageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
// 김용
@RestController
@RequiredArgsConstructor
@RequestMapping("/test/groupImage")
public class GroupImageController {

    private final GroupImageService groupImageService;
    private final CustomFileUtil customFileUtil;

    @GetMapping("/get/{id}")
    public ResponseEntity<Resource> getOne(@PathVariable(name = "id") Long id){
        return customFileUtil.getImage(id);
    }

    @PostMapping("/upload")
    public ResponseEntity<List<GroupImage>> uploadImage(@RequestParam("image") List<MultipartFile> image) {
        try {

            List<String> savedNames = customFileUtil.saveFile(image); // 파일 저장 및 저장된 이름 리스트 반환
            List<GroupImage> savedImages = groupImageService.saveImages(savedNames, image); // 서비스에 저장된 이름과 파일 전달
            return ResponseEntity.ok(savedImages);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body(null);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null); // 잘못된 파일 형식 처리
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, String>> removeImage(@PathVariable(name = "id") Long id) {
        groupImageService.removeImage(id);
        return ResponseEntity.ok(Map.of("result", "SUCCESS"));
    }
}

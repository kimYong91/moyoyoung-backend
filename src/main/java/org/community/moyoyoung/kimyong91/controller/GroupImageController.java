package org.community.moyoyoung.kimyong91.controller;

import lombok.RequiredArgsConstructor;
import org.community.moyoyoung.entity.GroupImage;
import org.community.moyoyoung.kimyong91.CustomFileUtil;
import org.community.moyoyoung.kimyong91.service.GroupImageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping()
public class GroupImageController {

    private final GroupImageService groupImageService;
    private final CustomFileUtil customFileUtil;

    @PostMapping()
    public ResponseEntity<List<GroupImage>> uploadFiles(@RequestParam("file") List<MultipartFile> file) {
        try {
            // 파일 저장 로직 호출
            List<String> savedNames = customFileUtil.saveFile(file); // 파일 저장 및 저장된 이름 리스트 반환
            List<GroupImage> savedImages = groupImageService.saveImages(savedNames, file); // 서비스에 저장된 이름과 파일 전달
            return ResponseEntity.ok(savedImages);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body(null);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null); // 잘못된 파일 형식 처리
        }
    }
}

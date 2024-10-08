package org.community.moyoyoung.kimyong91;

import lombok.RequiredArgsConstructor;
import net.coobird.thumbnailator.Thumbnails;
import org.community.moyoyoung.entity.GroupImage;
import org.community.moyoyoung.repository.GroupImageRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import jakarta.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
// 김용
@Component
@RequiredArgsConstructor
public class CustomFileUtil {

    @Value("${org.community.moyoyoung.upload}")
    private String uploadPath;

    private final GroupImageRepository groupImageRepository;

    @PostConstruct
    public void init() {
        File tempFolder = new File(uploadPath);

        if (!tempFolder.exists()) {
            tempFolder.mkdir();
        }

        uploadPath = tempFolder.getAbsolutePath();
    }

    public List<String> saveFile(List<MultipartFile> files) throws RuntimeException {
        List<String> uploadNames = new ArrayList<>();

        if (files == null || files.isEmpty()) {
            throw new RuntimeException("이미지가 업로드되지 않았습니다.");
        }

        for (MultipartFile file : files) {
            String contentType = file.getContentType();


            if (contentType != null && (contentType.startsWith("image/"))) {
                String savedName = UUID.randomUUID() + "_" + file.getOriginalFilename();
                Path savePath = Paths.get(uploadPath, savedName);

                if (Files.exists(savePath)) {
                    throw new RuntimeException("이미지가 이미 존재합니다.");
                }

                try {
                    Files.copy(file.getInputStream(), savePath);

//                    if (contentType != null && contentType.startsWith("image")) { // 이미지 파일일 경우
//                        // 이미지 파일에 대한 썸네일 경로 생성
//                        Path thumbnailPath = Paths.get(uploadPath, "s_" + savedName);
//
//                        // 원본 이미지를 200x200 크기로 축소하여 썸네일 생성 (비율을 유지하면서 가장 긴 쪽을 200으로 맞춤)
//                        Thumbnails.of(savePath.toFile())  // 원본 파일을 불러와서
//                                .size(200, 200)  // 썸네일 크기를 200x200으로 지정
//                                .toFile(thumbnailPath.toFile());  // 썸네일 이미지를 새로운 파일로 저장
//                    }

                    uploadNames.add(savedName);

                } catch (IOException e) {
                    throw new RuntimeException(e.getMessage());

                }
            } else {

                throw new RuntimeException("이미지만 업로드할 수 있습니다.");

            }

        }

        return uploadNames;
    }


    public ResponseEntity<Resource> getImage(Long id) {
        Optional<GroupImage> image = groupImageRepository.findById(id);

        GroupImage groupImage = image.orElse(new GroupImage());

        Resource resource = new FileSystemResource(uploadPath + File.separator + groupImage.getFileName());

        HttpHeaders headers = new HttpHeaders();

        try {

            headers.add(
                    "Content-Type",
                    Files.probeContentType(resource.getFile().toPath())
            );

        } catch (Exception e) {

            return ResponseEntity.internalServerError().build();
        }

        return ResponseEntity.ok().headers(headers).body(resource);
    }


    public void removeFile(String fileName) {
        Path filePath = Paths.get(uploadPath, fileName);

        try {
            Files.deleteIfExists(filePath);
        } catch (IOException e) {
            throw new RuntimeException("이미지 삭제 중 오류 발생: " + e.getMessage());
        }
    }
}

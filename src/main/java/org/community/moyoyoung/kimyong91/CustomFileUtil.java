package org.community.moyoyoung.kimyong91;

import lombok.RequiredArgsConstructor;
import org.community.moyoyoung.entity.GroupImage;
import org.community.moyoyoung.repository.GroupImageRepository;
import org.springframework.beans.factory.annotation.Value;
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
import java.util.UUID;

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
            return null;
        }

        for (MultipartFile file : files) {
            String contentType = file.getContentType();

            // MIME 타입이 이미지인지 확인 (image/jpeg, image/png 등)
            if (contentType != null && (contentType.equals("image/jpeg")
                    || (contentType.equals("image/png")))
                    || (contentType.equals("image/gif"))) {
                String savedName = UUID.randomUUID() + "_" + file.getOriginalFilename();
                Path savePath = Paths.get(uploadPath, savedName);

                try {
                    // 파일을 지정된 경로에 복사하여 저장
                    Files.copy(file.getInputStream(), savePath);
                    // 저장된 파일명을 리스트에 추가
                    uploadNames.add(savedName);
                } catch (IOException e) {
                    throw new RuntimeException(e.getMessage());
                }
            } else {
                throw new RuntimeException("이미지 파일만 업로드할 수 있습니다.");
            }
        }

        return uploadNames;
    }
}

package org.community.moyoyoung.kimyong91.service;

import lombok.RequiredArgsConstructor;
import org.community.moyoyoung.entity.GroupImage;
import org.community.moyoyoung.repository.GroupImageRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class GroupImageService {

    private final GroupImageRepository groupImageRepository;

    public List<GroupImage> saveImages(List<String> savedNames, List<MultipartFile> files) throws IOException {
        List<GroupImage> groupImages = new ArrayList<>();

        for (int i = 0; i < savedNames.size(); i++) {
            String originalFilename = files.get(i).getOriginalFilename();
            String savedName = savedNames.get(i);

            // MIME 타입과 파일 정보 저장
            GroupImage groupImage = GroupImage.builder()
                    .fileName(savedName)
                    .upLoadFileName(originalFilename)
                    .mimeType(files.get(i).getContentType())
                    .dueDate(LocalDate.now().plusDays(30)) // 유효기간 30일 설정
                    .build();

            // 데이터베이스에 저장
            groupImages.add(groupImageRepository.save(groupImage));
        }

        return groupImages;
    }
}

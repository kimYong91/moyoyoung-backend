package org.community.moyoyoung.kimyong91.service;

import lombok.RequiredArgsConstructor;
import org.community.moyoyoung.entity.GroupImage;
import org.community.moyoyoung.kimyong91.CustomFileUtil;
import org.community.moyoyoung.repository.GroupImageRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
// 김용
@Service
@Transactional
@RequiredArgsConstructor
public class GroupImageService {

//    private final GroupImageRepository groupImageRepository;
//    private final CustomFileUtil customFileUtil;
//
//    public List<GroupImage> saveImages(List<String> savedNames, List<MultipartFile> files) throws IOException {
//        List<GroupImage> groupImages = new ArrayList<>();
//
//        for (int i = 0; i < savedNames.size(); i++) {
//            String originalImageName = files.get(i).getOriginalFilename();
//            String savedName = savedNames.get(i);
//
//            GroupImage groupImage = GroupImage.builder()
//                    .fileName(savedName)
//                    .upLoadFileName(originalImageName)
//                    .mimeType(files.get(i).getContentType())
//                    .createDate(LocalDate.now())
//                    .build();
//
//            groupImages.add(groupImageRepository.save(groupImage));
//        }
//
//        return groupImages;
//    }
//
//    public void removeImage(Long id) {
//        GroupImage groupImage = groupImageRepository.findById(id).orElseThrow();
//
//        customFileUtil.removeFile(groupImage.getFileName());
//
//        groupImageRepository.deleteById(id);
//    }
}

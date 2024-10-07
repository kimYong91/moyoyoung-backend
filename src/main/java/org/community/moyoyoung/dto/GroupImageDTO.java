package org.community.moyoyoung.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.community.moyoyoung.entity.Group;
import org.community.moyoyoung.entity.GroupImage;
import org.community.moyoyoung.entity.Meeting;
import org.community.moyoyoung.entity.MyUser;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

// 김용
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GroupImageDTO {
    private Long id;
    private String fileName;
    private String upLoadFileName;
    private LocalDate createDate;
    private String mimeType;
    private Group group;
}


package org.community.moyoyoung.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.community.moyoyoung.entity.Group;

import java.time.LocalDate;

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


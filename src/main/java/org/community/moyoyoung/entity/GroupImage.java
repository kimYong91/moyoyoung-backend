package org.community.moyoyoung.entity;

import jakarta.persistence.OneToOne;
import lombok.*;

import java.time.LocalDate;


@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
// 소모임 프로필 사진
public class GroupImage {

    private String fileName;
    private String upLoadFileName;
    private LocalDate dueDate;
    private String mimeType;

    @OneToOne
    private Group group;
}

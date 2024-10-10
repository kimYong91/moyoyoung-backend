package org.community.moyoyoung.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
// 김용
// 소모임 프로필 사진
@Entity
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GroupImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileName;
    private String upLoadFileName;
    private LocalDate createDate;
    private String mimeType;

}

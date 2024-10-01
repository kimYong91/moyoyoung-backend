package org.community.moyoyoung.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
// 소모임 프로필 사진
public class GroupImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileName;
    private String upLoadFileName;
    private LocalDate dueDate;
    private String mimeType;

    @OneToOne
    private Group group;
}

package org.community.moyoyoung.entity;

import jakarta.persistence.*;
import org.springframework.transaction.annotation.Transactional;
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
@Transactional
public class GroupImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileName;
    private String upLoadFileName;
    private LocalDate createDate;
    private String mimeType;
    private boolean delFlag;  // 삭제 여부 (true일 경우 삭제된 것으로 간주)

    @OneToOne
    private Group group; // group_id


}

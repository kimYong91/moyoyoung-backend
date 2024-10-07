package org.community.moyoyoung.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
// 김용
// 게시판 첨부 이미지
@Entity
@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileName;
    private String upLoadFileName;
    private LocalDate createDate;
    private String mimeType;

    @OneToOne
    private Post post;

    @OneToOne
    private MyUser myUser;

    public void setFileName(String originalFilename) {
    }

    public void setFilePath(String path) {
    }
}


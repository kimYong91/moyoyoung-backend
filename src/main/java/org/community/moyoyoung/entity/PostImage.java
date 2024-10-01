package org.community.moyoyoung.entity;


import jakarta.persistence.OneToOne;
import lombok.*;

import java.time.LocalDate;

@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
// 게시판 첨부 이미지
public class PostImage {

    private String fileName;
    private String upLoadFileName;
    private LocalDate dueDate;
    private String mimeType;

    @OneToOne
    private Post post;

    @OneToOne
    private MyUser myUser;

}


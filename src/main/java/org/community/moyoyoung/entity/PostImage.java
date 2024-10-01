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
// 게시판 첨부 이미지
public class PostImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileName;
    private String upLoadFileName;
    private LocalDate dueDate;
    private String mimeType;

    @OneToOne
    private Post post;

    @OneToOne
    private MyUser myUser;

}


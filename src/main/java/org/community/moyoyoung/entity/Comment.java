package org.community.moyoyoung.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "tbl_comment")
@Builder
// 댓글
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 200)
    private String content;
    private String userNickname;
    private String username;
    private LocalDateTime createDate;
    private boolean checkOnline;

    @ManyToOne
    private Post post;

    @OneToOne
    private MyUser myUser;
}

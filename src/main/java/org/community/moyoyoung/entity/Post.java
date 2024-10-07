package org.community.moyoyoung.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
// 김용
// 게시판
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "tbl_post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    private String nickName;
    private String name;
    private LocalDateTime createDate;
    private LocalDateTime modifiedDate; // 수정일자

    private boolean delFlag;
    private boolean checkOnline;




    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private PostImage postImage;


    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
    private List<Comment> commentList;


    @ManyToOne
    private MyUser myUser;
}
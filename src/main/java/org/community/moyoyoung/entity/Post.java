package org.community.moyoyoung.entity;

import jakarta.persistence.*;
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

    @Column(nullable = false)
    private LocalDateTime createDate;
    @Column(nullable = false)
    private LocalDateTime modifiedDate; // 수정일자

    private Boolean delFlag;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private PostImage postImage;

    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private List<Comment> commentList;

    @OneToOne
    @PrimaryKeyJoinColumn
    private MyUser myUser;

    public void changeTitle(String title){
        this.title = title;
    }

    public void changeModifiedDate(LocalDateTime modifiedDate){
        this.modifiedDate = modifiedDate;
    }

}
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
    @NotBlank(message = "게시글 제목은 필수입니다.")
    private String title;

    @Column(nullable = false)
    @NotBlank(message = "게시글 내용은 필수입니다.")
    private String content;

    @Column(nullable = false)
    private LocalDateTime createDate;

    @Column(nullable = false)
    private LocalDateTime modifiedDate; // 수정일자

    @Column(nullable = false)
    private Boolean delFlag = false; // 기본값을 false로 설정

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private PostImage postImage;

    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private List<Comment> commentList;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private MyUser myUser;

    public void changeTitle(String title) {
        this.title = title;
    }

    public void changeModifiedDate(LocalDateTime modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
}

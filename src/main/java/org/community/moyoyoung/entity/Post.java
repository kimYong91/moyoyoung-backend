package org.community.moyoyoung.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
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
    @Builder.Default
    private Boolean delFlag = false; // 기본값을 false로 설정

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private PostImage postImage;

    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private List<Comment> commentList;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private MyUser myUser;

    @PrePersist
    protected void onCreate() {
        this.createDate = LocalDateTime.now(); // 현재 시간으로 생성일자 설정
    }

    public void changeTitle(String title) {
        this.title = title;
    }

    public void changeModifiedDate(LocalDateTime modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
}

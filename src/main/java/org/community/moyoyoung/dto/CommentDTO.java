package org.community.moyoyoung.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.community.moyoyoung.entity.Comment;
import org.community.moyoyoung.entity.MyUser;

import java.time.LocalDateTime;

// 양수연
@Data
@AllArgsConstructor
@Builder
public class CommentDTO {

    private Long id;
    private String content;
    private MyUser myUser; // MyUser 객체를 포함
    private LocalDateTime createdDate;

    // 생성자 - 댓글 생성 시 필요한 정보만 받는 생성자
    public CommentDTO(String content, MyUser myUser) {
        this.content = content;
        this.myUser = myUser;
    }

    public CommentDTO(Comment comment) {
        this.id = comment.getId();
        this.content = comment.getContent();
        this.myUser = comment.getMyUser();

        // 작성자가 온라인 상태라면 MyUser의 name 필드를 null로 설정
        if (comment.isCheckOnline()) {
            this.myUser.setName(null);
        }

        this.createdDate = comment.getCreateDate();
    }

    public Long getId() {
        return id; // 댓글 ID 반환
    }

    public void setId(Long id) {
        this.id = id; // 댓글 ID 설정
    }

    public String getContent() {
        return content; // 댓글 내용 반환
    }

    public void setContent(String content) {
        // 내용 유효성 검사 추가 가능
        if (content == null || content.trim().isEmpty()) {
            throw new IllegalArgumentException("댓글 내용은 비어있을 수 없습니다.");
        }
        this.content = content; // 댓글 내용 설정
    }

    public MyUser getMyUser() {
        return myUser; // 작성자 정보 반환
    }

    public void setMyUser(MyUser myUser) {
        this.myUser = myUser; // 작성자 정보 설정
    }

    public LocalDateTime getCreatedDate() {
        return createdDate; // 댓글 생성일 반환
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate; // 댓글 생성일 설정
    }
}

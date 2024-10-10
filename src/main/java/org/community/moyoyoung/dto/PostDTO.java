package org.community.moyoyoung.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.community.moyoyoung.entity.Comment;
import org.community.moyoyoung.entity.MyUser;
import org.community.moyoyoung.entity.PostImage;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


// 양수연

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostDTO {

    private Long id;

    private String title;
    private String content;

    @JsonIgnore
    private PostImage postImage;

    private List<Comment> commentList;

    private List<MultipartFile> files;

//    private MyUser myUser;

    private boolean checkOnline;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDate createDate;

//    public String getUserName() {
//        if (myUser != null) {
//            return myUser.isCheckOnline() ? myUser.getNickname() : myUser.getName();
//        }
//        return null; // MyUser가 null일 경우 처리
//    }

//    public String getUserName() {
//        if (myUser != null) {
//            return checkOnline ? myUser.getNickname() : myUser.getName();
//        }
//        return "Unknown User";
//    }
}
package org.community.moyoyoung.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.community.moyoyoung.entity.Comment;
import org.community.moyoyoung.entity.Meeting;
import org.community.moyoyoung.entity.MyUser;
import org.community.moyoyoung.entity.PostImage;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostDTO {

    private Long id;
    private String title;
    private String contents;
    private LocalDate dueDate;


    private Meeting meetingList;

    private PostImage postImage;

    private List<Comment> commentList;

    private MyUser myUser;
}

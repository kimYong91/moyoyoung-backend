package org.community.moyoyoung.dto;

import lombok.*;
import org.community.moyoyoung.entity.Comment;
import org.community.moyoyoung.entity.PostImage;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostDetailDTO {

    private Long Id;

    private String title;
    private String content;
    private String nickName;
    private String name;

    private List<Comment> commentList;
    private PostImage postImage;

    private LocalDateTime dueDate;
    private LocalDateTime modifiedDate;

}

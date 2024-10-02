package org.community.moyoyoung.dto;

import lombok.*;
import org.community.moyoyoung.entity.Comment;
import org.community.moyoyoung.entity.PostImage;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostDetailDTO {

    private String title;
    private String content;
    private List<Comment> commentList;
    private PostImage postImage;


}

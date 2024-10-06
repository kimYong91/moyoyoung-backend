package org.community.moyoyoung.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.community.moyoyoung.entity.MyUser;
import org.community.moyoyoung.entity.Post;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentDTO {

    private Long Id;
    private String content;
    private LocalDateTime dueDate;
    private MyUser myUser;

    public CommentDTO(Long id, String content, LocalDateTime dueDate) {
    }
}

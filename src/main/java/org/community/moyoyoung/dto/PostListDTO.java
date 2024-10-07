
package org.community.moyoyoung.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.community.moyoyoung.entity.Comment;
import org.community.moyoyoung.entity.MyUser;
import org.community.moyoyoung.entity.PostImage;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

// 양수연
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostListDTO {

    private Long id;
    private String title;
    private MyUser myUser;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dueDate;

    public String getUserName() {
        if (myUser != null) {
            return myUser.isCheckOnline() ? myUser.getNickname() : myUser.getName();
        }
        return null; // MyUser가 null일 경우 처리
    }
}


package org.community.moyoyoung.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.community.moyoyoung.entity.MyUser;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

// 양수연
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostListDTO {

    private Long id;
    private String title;
    private String name;
    private String nickname;


    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createDate;


}

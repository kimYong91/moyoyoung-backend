
package org.community.moyoyoung.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.community.moyoyoung.entity.MyUser;

import java.time.LocalDateTime;

// 양수연
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "tbl_postlist")
public class PostListDTO {

    private Long id;
    private String title;
    private MyUser myUser;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createDate;


}

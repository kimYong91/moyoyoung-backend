package org.community.moyoyoung.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostImageDTO {

    private Long id;
    private String fileName;
    private String upLoadFileName;
    private String mimeType;
    private LocalDateTime createDate;

}

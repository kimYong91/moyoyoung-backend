package org.community.moyoyoung.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// 양수연
@Builder
@Getter
@Setter
public class PostCreateDTO {

    @NotBlank(message = "제목을 입려하세요.")
    private String title;

    @NotBlank(message = "내용을 입력하세요.")
    private String content;

    @Column(name = "modified_date", nullable = false)
    private LocalDateTime modifiedDate;

    private Long myUserId; // 현재 사용자 정보

    @Builder.Default
    private List<MultipartFile> files = new ArrayList<>();

    @Builder.Default
    private List<String> uploadFileNames = new ArrayList<>();
}





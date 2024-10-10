package org.community.moyoyoung.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.community.moyoyoung.entity.MyUser;
import org.community.moyoyoung.entity.PostImage;
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

    private MyUser myUser;

    private PostImage postImage;

    @Builder.Default
    private List<MultipartFile> files = new ArrayList<>();


    @Builder.Default
    private List<MultipartFile> file = new ArrayList<>();
    @Builder.Default
    private List<String> uploadFileName = new ArrayList<>();
}





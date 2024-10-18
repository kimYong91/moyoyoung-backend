package org.community.moyoyoung.dto;

import lombok.*;
import org.community.moyoyoung.entity.Group;
import org.community.moyoyoung.entity.GroupImage;
import org.community.moyoyoung.entity.Meeting;
import org.community.moyoyoung.entity.MyUser;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
// 김용
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GroupDTO {

    private Long id;
    private boolean checkOnline;
    private String country;
    private String category;
    private String title;
    private String content;
    private LocalDate createDate;

    private List<PostDTO> posts;  // 최신 포스트 리스트 필드

    private Meeting meeting;

    private GroupImage groupImage;

    private Group group;


    @Builder.Default
    private List<MyUser> member = new ArrayList<>();

    private MyUser ownUser;

    @Builder.Default
    private List<MultipartFile> file = new ArrayList<>();
//    @Builder.Default
//    private List<String> uploadFileName = new ArrayList<>();

}


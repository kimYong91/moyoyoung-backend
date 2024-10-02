package org.community.moyoyoung.dto;

import lombok.*;
import org.community.moyoyoung.entity.GroupImage;
import org.community.moyoyoung.entity.Meeting;
import org.community.moyoyoung.entity.MyUser;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
    private LocalDate dueDate;
    private String userNickName;
    private String name;

    private List<PostDTO> posts;  // 최신 포스트 리스트 필드


    private Meeting meetingList;

    private GroupImage groupImage;

    @Builder.Default
    private List<MyUser> member = new ArrayList<>();

    private MyUser ownUser;

    @Builder.Default
    private List<MultipartFile> files = new ArrayList<>();
    @Builder.Default
    private List<String> uploadFileName = new ArrayList<>();

}


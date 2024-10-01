package org.community.moyoyoung.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.community.moyoyoung.entity.GroupImage;
import org.community.moyoyoung.entity.Meeting;
import org.community.moyoyoung.entity.MyUser;

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
    private String contents;
    private LocalDate dueDate;


    private Meeting meetingList;

    private GroupImage groupImage;

    private List<MyUser> member = new ArrayList<>();

    private MyUser ownUser;

}


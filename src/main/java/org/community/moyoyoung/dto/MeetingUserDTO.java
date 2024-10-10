package org.community.moyoyoung.dto;

import lombok.*;
import org.community.moyoyoung.entity.MyUser;

import java.util.List;
// 김용
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MeetingUserDTO {

    private Long id;
//    private Long meetingId;
//    private List<MyUser> userIds;

    private Long meetingId;
    private List<Long> myUserIds;
}

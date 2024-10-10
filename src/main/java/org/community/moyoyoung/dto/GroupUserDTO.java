package org.community.moyoyoung.dto;

import lombok.*;
import org.community.moyoyoung.entity.Group;
import org.community.moyoyoung.entity.MyUser;

// 김용
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GroupUserDTO {

    private Group group;
    private MyUser user;

//    private Long group;
//    private Long user;

}

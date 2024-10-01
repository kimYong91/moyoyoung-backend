package org.community.moyoyoung.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.community.moyoyoung.entity.Group;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MyUserDTO {

    private Long id;
    private String username;
    private String nickName;
    private String password;
    private String name;
    private String phoneNumber;


    private Group ownGroup;

    private Group group;
}

package org.community.moyoyoung.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.community.moyoyoung.entity.Group;

import java.util.ArrayList;
import java.util.List;

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

    @Builder.Default
    private List<Group> ownGroup = new ArrayList<>();

    @Builder.Default
    private List<Group> group = new ArrayList<>();
}

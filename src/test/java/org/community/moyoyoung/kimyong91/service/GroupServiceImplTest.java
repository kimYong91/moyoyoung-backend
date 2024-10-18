package org.community.moyoyoung.kimyong91.service;

import org.community.moyoyoung.dto.userStateDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GroupServiceImplTest {

    @Autowired
    private GroupService groupService;

    @Test
    public void getGroupUserState() {
        Long groupId = 2L;
        Long userId = null;
        userStateDTO result = groupService.getGroupUserState(groupId, userId);
        System.out.println(result.getGroupId());
        System.out.println(result.getGroupTitle());
        System.out.println(result.getUserId());
        System.out.println(result.getUserName());
        System.out.println(result.getIsOwner());
        System.out.println(result.getIsGroup());
        System.out.println(result.getIsMeeting());

    }


//    @Test
//    public void register() {
//        for (int i = 0; i < 100; i++) {
//            GroupDTO groupDTO = GroupDTO.builder()
//                    .checkOnline(false)
//                    .country("부산" + i)
//                    .category("운동" + i)
//                    .title("제목" + i)
//                    .content("내용" + i)
//                    .build();
//            groupService.register(groupDTO);
//        }
//        for (int i = 0; i < 100; i++) {
//            GroupDTO groupDTO = GroupDTO.builder()
//                    .checkOnline(true)
//                    .country("부산" + i)
//                    .category("운동" + i)
//                    .title("제목" + i)
//                    .content("내용" + i)
//                    .build();
//            groupService.register(groupDTO);
//        }
//    }
}
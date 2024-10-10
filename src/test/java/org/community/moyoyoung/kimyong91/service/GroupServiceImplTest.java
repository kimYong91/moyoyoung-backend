package org.community.moyoyoung.kimyong91.service;

import org.community.moyoyoung.dto.GroupDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GroupServiceImplTest {

    @Autowired
    private GroupService groupService;

    @Test
    public void register() {
        for (int i = 0; i < 100; i++) {
            GroupDTO groupDTO = GroupDTO.builder()
                    .checkOnline(false)
                    .country("부산" + i)
                    .category("운동" + i)
                    .title("제목" + i)
                    .content("내용" + i)
                    .build();
            groupService.register(groupDTO, null);
        }
        for (int i = 0; i < 100; i++) {
            GroupDTO groupDTO = GroupDTO.builder()
                    .checkOnline(true)
                    .country("부산" + i)
                    .category("운동" + i)
                    .title("제목" + i)
                    .content("내용" + i)
                    .build();
            groupService.register(groupDTO, null);
        }
    }
}
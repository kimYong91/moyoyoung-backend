package org.community.moyoyoung.kimyong91;


import org.community.moyoyoung.dto.GroupDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GroupServiceImplTest {

    @Autowired
    private GroupService groupService;

    @Test
    @DisplayName("서비스 호출 테스트")
    public void get() {
        Long id = 1L;
        groupService.getOne(id);
    }

    @Test
    @DisplayName("서비스 입력 테스트")
    public void register() {
        GroupDTO groupDTO = GroupDTO.builder()
                .checkOnline(true)
                .country("부산")
                .category("운동")
                .title("서비스 테스트")
                .contents("서비스 테스트 내용")
                .build();
        groupService.register(groupDTO);
    }

    @Test
    @DisplayName("서비스 수정 테스트")
    public void modify() {
        GroupDTO groupDTO = GroupDTO.builder()
                .id(2L)
                .checkOnline(true)
                .country("서울 수정")
                .category("여행 수정")
                .title("수정 테스트")
                .contents("내용 수정 테스트")
                .dueDate(LocalDate.now())
                .build();
        groupService.modify(groupDTO);
    }

    @Test
    @DisplayName("서비스 삭제 테스트")
    public void remove() {
        Long id = 2L;
        groupService.remove(id);
    }

}
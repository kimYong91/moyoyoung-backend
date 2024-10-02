package org.community.moyoyoung.kimyong91;


import org.community.moyoyoung.dto.*;
import org.community.moyoyoung.entity.Meeting;
import org.community.moyoyoung.entity.Post;
import org.community.moyoyoung.kimyong91.service.GroupService;
import org.community.moyoyoung.repository.MeetingRepository;
import org.community.moyoyoung.repository.PostRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@SpringBootTest
class GroupServiceImplTest {

    private static final Logger log = LoggerFactory.getLogger(GroupServiceImplTest.class);

    @Autowired
    private GroupService groupService;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private MeetingRepository meetingRepository;

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
                .content("서비스 테스트 내용")
                .build();
        groupService.register(groupDTO);
    }

    @Test
    @DisplayName("서비스 테스트용 정보 입력")
    public void register2() {
        for (int i = 0; i < 100; i++) {

            GroupDTO groupDTO = GroupDTO.builder()
                    .checkOnline(true)
                    .country("서울")
                    .category("문화")
                    .title("서비스 테스트 " + i)
                    .content("서비스 테스트 내용" + i)
                    .build();
            groupService.register(groupDTO);
        }
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
                .content("내용 수정 테스트")
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
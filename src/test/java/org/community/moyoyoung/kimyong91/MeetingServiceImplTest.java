package org.community.moyoyoung.kimyong91;

import org.community.moyoyoung.dto.MeetingDTO;
import org.community.moyoyoung.kimyong91.service.MeetingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MeetingServiceImplTest {

    @Autowired
    private MeetingService meetingService;


    @Test
    void register() {
        MeetingDTO meetingDTO = MeetingDTO.builder()
                .title("정기모임 제목")
                .meetingDate("정기모임 모임날짜")
                .content("정기모임 내용")
                .build();

        meetingService.register(meetingDTO);
    }

    @Test
    void modify() {
        MeetingDTO meetingDTO = MeetingDTO.builder()
                .id(1L)
                .title("정기모임 제목 수정")
                .meetingDate("정기모임 모임날짜 수정")
                .content("정기모임 내용 수정")
                .build();

        meetingService.modify(meetingDTO);
    }

    @Test
    void remove() {
        Long id = 1L;
        meetingService.remove(id);
    }
}
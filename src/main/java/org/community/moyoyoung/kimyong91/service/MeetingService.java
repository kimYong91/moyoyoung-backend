package org.community.moyoyoung.kimyong91.service;

import org.community.moyoyoung.dto.MeetingDTO;
import org.springframework.stereotype.Service;
// 김용
@Service
public interface MeetingService {

    MeetingDTO get(Long id);

    Long register(MeetingDTO meetingDTO);

    void modify(MeetingDTO meetingDTO);

    void remove(Long id);
}

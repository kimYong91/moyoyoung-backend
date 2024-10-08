package org.community.moyoyoung.kimyong91.service;

import org.community.moyoyoung.dto.MeetingUserDTO;
import org.springframework.stereotype.Service;

import java.util.List;
// 김용
@Service
public interface MeetingUserService {

    Long join(MeetingUserDTO meetingUserDTO);

    void remove(Long id);

    List<MeetingUserDTO> getListAll();
}

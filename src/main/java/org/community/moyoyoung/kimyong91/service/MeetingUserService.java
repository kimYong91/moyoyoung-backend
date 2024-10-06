package org.community.moyoyoung.kimyong91.service;

import org.community.moyoyoung.dto.MeetingUserDTO;
import org.community.moyoyoung.entity.MeetingUser;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MeetingUserService {

    Long register(MeetingUserDTO meetingUserDTO);

    void remove(Long id);

    List<MeetingUserDTO> getListAll();
}

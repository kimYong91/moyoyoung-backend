package org.community.moyoyoung.kimyong91.service;

import org.community.moyoyoung.dto.MeetingUserDTO;
import org.community.moyoyoung.entity.MeetingUser;
import org.springframework.stereotype.Service;

import java.util.List;
// 김용
@Service
public interface MeetingUserService {

    MeetingUser join(Long id, Long userId);

    void remove(Long id);

    List<MeetingUserDTO> getListAll();
}

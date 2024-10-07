package org.community.moyoyoung.kimyong91.service;

import org.community.moyoyoung.dto.*;
import org.springframework.stereotype.Service;
// 김용
@Service
public interface GroupService{

    Long register(GroupDTO groupDTO);

    GroupDTO getOne(Long id);

    void modify(GroupDTO groupDTO);

    void remove(Long id);

    PageResponseDTO<PostMiniDTO> getPostMiniUserList(GroupDTO groupDTO, PageRequestDTO pageRequestDTO);

    MeetingDTO getMeeting(Long id);
}

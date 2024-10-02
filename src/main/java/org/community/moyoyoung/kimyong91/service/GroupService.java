package org.community.moyoyoung.kimyong91.service;

import org.community.moyoyoung.dto.*;
import org.springframework.stereotype.Service;

@Service
public interface GroupService{

    Long register(GroupDTO groupDTO);

    GroupDTO getOne(Long id);

    void modify(GroupDTO groupDTO);

    void remove(Long id);

    PageResponseDTO<PostMiniDTO> getPostMiniList(GroupDTO groupDTO, PageRequestDTO pageRequestDTO);

    MeetingDTO getMeeting(Long id);
}

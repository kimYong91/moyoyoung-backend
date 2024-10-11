package org.community.moyoyoung.kimyong91.service;

import org.community.moyoyoung.dto.*;
import org.community.moyoyoung.entity.Group;
import org.community.moyoyoung.entity.MyUser;
import org.springframework.stereotype.Service;

import java.util.List;

// 김용
@Service
public interface GroupService{

    Long register(GroupDTO groupDTO, MyUser myUser);

    GroupDTO getOne(Long id);

    void modify(GroupDTO groupDTO);

    void remove(Long id);

    MeetingDTO getMeeting(Long id);

    List<PostMiniDTO> getPostMiniList(Long id);

    GroupDetailDTO getGroupDetail(Long id);

    userStateDTO getGroupUserState(Long groupId, Long userId);
}

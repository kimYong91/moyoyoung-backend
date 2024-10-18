package org.community.moyoyoung.kimyong91.service;

import org.community.moyoyoung.dto.*;
import org.community.moyoyoung.entity.Group;
import org.springframework.stereotype.Service;

import java.util.List;

// 김용
@Service
public interface GroupService{

    Long register(GroupDTO groupDTO);

    GroupDTO getOne(Long groupId);

    void modify(GroupDTO groupDTO);

    void updateToRemove(Long groupId);

    void realRemove(Long groupId);

    MeetingDTO getMeeting(Long groupId);

    List<PostMiniDTO> getPostMiniList(Long groupId);

    GroupDetailDTO getGroupDetail(Long groupId);

    Group getGroup(Long groupId);

    userStateDTO getGroupUserState(Long groupId, Long userId);

}

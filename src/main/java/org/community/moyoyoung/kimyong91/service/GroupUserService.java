package org.community.moyoyoung.kimyong91.service;

import org.community.moyoyoung.dto.GroupUserDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GroupUserService{

    GroupUserDTO groupJoin(Long groupId, Long userId);

    void groupUserRemove(Long userId);

    List<GroupUserDTO> getGroupUserListAll();
}

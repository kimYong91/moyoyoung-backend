package org.community.moyoyoung.kimyong91.service;

import org.community.moyoyoung.dto.GroupUserDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GroupUserService{

    GroupUserDTO groupJoin(Long groupId);

    void groupUserSecession(); // 그룹 탈퇴

    void groupOwnUserChange(Long newOwnUserId, Long groupId);

    List<GroupUserDTO> getGroupUserListAll();
}

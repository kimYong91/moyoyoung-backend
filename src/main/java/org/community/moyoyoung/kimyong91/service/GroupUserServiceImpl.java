package org.community.moyoyoung.kimyong91.service;

import lombok.RequiredArgsConstructor;
import org.community.moyoyoung.dto.GroupUserDTO;
import org.community.moyoyoung.entity.Group;
import org.community.moyoyoung.entity.GroupUser;
import org.community.moyoyoung.entity.MyUser;
import org.community.moyoyoung.repository.GroupRepository;
import org.community.moyoyoung.repository.GroupUserRepository;
import org.community.moyoyoung.repository.MyUserRepository;
import org.community.moyoyoung.samgak0.services.AuthService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

// 김용
@Service
@Transactional
@RequiredArgsConstructor
public class GroupUserServiceImpl implements GroupUserService{

    private final GroupUserRepository groupUserRepository;
    private final GroupRepository groupRepository;
    private final MyUserRepository myUserRepository;
    private final ModelMapper modelMapper;
    private final AuthService authService;

    @Override
    public GroupUserDTO groupJoin(Long groupId) {
        MyUser myUser = authService.getLoginData().orElseThrow();
        Long userId = myUser.getId();
        Group group = groupRepository.findById(groupId).orElseThrow();
        MyUser user = myUserRepository.findById(userId).orElseThrow();

        GroupUser groupUser = GroupUser.builder()
                .group(group)
                .user(user)
                .build();

        groupUserRepository.save(groupUser);

        GroupUserDTO groupUserDTO = modelMapper.map(groupUser, GroupUserDTO.class);

        return groupUserDTO;
    }

    @Override
    public void groupUserSecession() {
        MyUser myUser = authService.getLoginData().orElseThrow();
        Long userId = myUser.getId();
        groupUserRepository.deleteById(userId);
    }

    @Override
    public void groupOwnUserChange(Long newOwnUserId, Long groupId) {

        MyUser myUser = authService.getLoginData().orElseThrow();
        Long oldOwnUserId = myUser.getId();

        groupUserRepository.groupOwnUserChange(newOwnUserId, oldOwnUserId, groupId);
    }

    @Override
    public List<GroupUserDTO> getGroupUserListAll() {

        List<GroupUser> all = groupUserRepository.findAll();

        List<GroupUserDTO> map = modelMapper.map(all, new TypeToken<List<GroupUserDTO>>() {}.getType());

        return map;
    }
}

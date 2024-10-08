package org.community.moyoyoung.kimyong91.service;

import lombok.RequiredArgsConstructor;
import org.community.moyoyoung.dto.GroupOfflineListDTO;
import org.community.moyoyoung.dto.GroupOnlineListDTO;
import org.community.moyoyoung.entity.Group;
import org.community.moyoyoung.repository.GroupRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class GroupListServiceImpl implements GroupListService {

    private final GroupRepository groupRepository;
    private final ModelMapper modelMapper;


    public List<GroupOnlineListDTO> getGroupOnlineList() {
        List<Group> groupOnlineList = groupRepository.getGroupOnlineList();
        List<GroupOnlineListDTO> groupOnlineListDTO = new ArrayList<>();
        for (Group group : groupOnlineList) {
            groupOnlineListDTO.add(modelMapper.map(group, GroupOnlineListDTO.class));
        }
        return groupOnlineListDTO;
    }

    public List<GroupOfflineListDTO> getGroupOfflineList() {

        List<Group> groupOfflineList = groupRepository.getGroupOfflineList();
        List<GroupOfflineListDTO> groupOfflineListDTO = new ArrayList<>();
        for (Group group : groupOfflineList) {
            groupOfflineListDTO.add(modelMapper.map(group, GroupOfflineListDTO.class));
        }
        return groupOfflineListDTO;
    }

}


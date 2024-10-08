package org.community.moyoyoung.kimyong91.service;

import lombok.RequiredArgsConstructor;
import org.community.moyoyoung.dto.GroupOfflineListDTO;
import org.community.moyoyoung.dto.GroupOnlineListDTO;
import org.community.moyoyoung.entity.Group;
import org.community.moyoyoung.entity.GroupImage;
import org.community.moyoyoung.kimyong91.CustomFileUtil;
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
    private final CustomFileUtil customFileUtil;


    @Override
    public List<GroupOfflineListDTO> getGroupOfflineList() {

        List<Group> groupOfflineList = groupRepository.getGroupOnlineList();
        List<GroupOfflineListDTO> groupOfflineListDTO = new ArrayList<>();

        for (int i = 0; i < groupOfflineList.size(); i++) {
            groupOfflineListDTO.add(modelMapper.map(groupOfflineList.get(i), GroupOfflineListDTO.class));
            GroupImage fileName = groupOfflineList.get(i).getGroupImage();
            groupOfflineListDTO.get(i).setGroupImage(fileName);
            Long id = groupOfflineList.get(i).getGroupImage().getId();
            customFileUtil.getImage(id);
        }

        return groupOfflineListDTO;
    }

    @Override
    public List<GroupOnlineListDTO> getGroupOnlineList() {

        List<Group> groupOnlineList = groupRepository.getGroupOnlineList();
        List<GroupOnlineListDTO> groupOnlineListDTO = new ArrayList<>();

        for (int i = 0; i < groupOnlineList.size(); i++) {
            groupOnlineListDTO.add(modelMapper.map(groupOnlineList.get(i), GroupOnlineListDTO.class));
            GroupImage fileName = groupOnlineList.get(i).getGroupImage();
            groupOnlineListDTO.get(i).setGroupImage(fileName);
            Long id = groupOnlineList.get(i).getGroupImage().getId();
            customFileUtil.getImage(id);
        }

        return groupOnlineListDTO;

    }


}


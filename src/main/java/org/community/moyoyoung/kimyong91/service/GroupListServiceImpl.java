package org.community.moyoyoung.kimyong91.service;

import lombok.RequiredArgsConstructor;
import org.community.moyoyoung.dto.GroupOfflineDTO;
import org.community.moyoyoung.dto.GroupOnlineDTO;
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


//    @Override
//    public List<GroupOfflineDTO> getGroupOfflineList() {
//
//        List<Group> groupOfflineList = groupRepository.getGroupOnlineList();
//        List<GroupOfflineDTO> groupOfflineListDTO = new ArrayList<>();
//
//        for (int i = 0; i < groupOfflineList.size(); i++) {
//            groupOfflineListDTO.add(modelMapper.map(groupOfflineList.get(i), GroupOfflineDTO.class));
//            GroupImage fileName = groupOfflineList.get(i).getGroupImage();
//            groupOfflineListDTO.get(i).setGroupImage(fileName);
//            Long id = groupOfflineList.get(i).getGroupImage().getId();
//            customFileUtil.getImage(id);
//        }
//
//        return groupOfflineListDTO;
//    }

    @Override
    public List<GroupOfflineDTO> getGroupOfflineList() {

        List<Group> groupOfflineList = groupRepository.getGroupOnlineList();
        List<GroupOfflineDTO> groupOfflineListDTO = new ArrayList<>();

        for (int i = 0; i < groupOfflineList.size(); i++) {
            groupOfflineListDTO.add(modelMapper.map(groupOfflineList.get(i), GroupOfflineDTO.class));
            GroupImage groupImage = groupOfflineList.get(i).getGroupImage();
            groupOfflineListDTO.get(i).setGroupImageId(groupImage.getId());
            Long id = groupOfflineList.get(i).getGroupImage().getId();
            customFileUtil.getImage(id);
        }

        return groupOfflineListDTO;
    }



//    @Override
//    public List<GroupOnlineDTO> getGroupOnlineList() {
//
//        List<Group> groupOnlineList = groupRepository.getGroupOnlineList();
//        List<GroupOnlineDTO> groupOnlineListDTO = new ArrayList<>();
//
//        for (int i = 0; i < groupOnlineList.size(); i++) {
//            groupOnlineListDTO.add(modelMapper.map(groupOnlineList.get(i), GroupOnlineDTO.class));
//            GroupImage fileName = groupOnlineList.get(i).getGroupImage();
//            groupOnlineListDTO.get(i).setGroupImage(fileName);
//            Long id = groupOnlineList.get(i).getGroupImage().getId();
//            customFileUtil.getImage(id);
//        }
//
//        return groupOnlineListDTO;
//    }

    @Override
    public List<GroupOnlineDTO> getGroupOnlineList() {

        List<Group> groupOnlineList = groupRepository.getGroupOnlineList();
        List<GroupOnlineDTO> groupOnlineListDTO = new ArrayList<>();

        for (int i = 0; i < groupOnlineList.size(); i++) {
            groupOnlineListDTO.add(modelMapper.map(groupOnlineList.get(i), GroupOnlineDTO.class));
            GroupImage groupImage = groupOnlineList.get(i).getGroupImage();
            groupOnlineListDTO.get(i).setGroupImageId(groupImage.getId());
            Long id = groupOnlineList.get(i).getGroupImage().getId();
            customFileUtil.getImage(id);
        }

        return groupOnlineListDTO;
    }


}


package org.community.moyoyoung.kimyong91.service;

import lombok.RequiredArgsConstructor;
import org.community.moyoyoung.dto.GroupOfflineDTO;
import org.community.moyoyoung.dto.GroupOnlineDTO;
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
public class GroupSearchService {

    private final GroupRepository groupRepository;
    private final ModelMapper modelMapper;

    public List<GroupOnlineDTO> searchCategoryGroupOnlineList(String category) {
        List<Group> searchGroupOnlineList = groupRepository.searchCategoryGroupOnlineList(category);
        List<GroupOnlineDTO> searchGroupOnlineDTOList = new ArrayList<>();

        for (int i = 0; i < searchGroupOnlineList.size(); i++) {
            searchGroupOnlineDTOList.add(modelMapper.map(searchGroupOnlineList.get(i), GroupOnlineDTO.class));
        }

        return searchGroupOnlineDTOList;
    }

    public List<GroupOfflineDTO> searchCategoryGroupOfflineList(String groupCategory) {
        List<Group> searchGroupOfflineList = groupRepository.searchCategoryGroupOfflineList(groupCategory);
        List<GroupOfflineDTO> searchGroupOfflineDTOList = new ArrayList<>();

        for (int i = 0; i < searchGroupOfflineList.size(); i++) {
            searchGroupOfflineDTOList.add(modelMapper.map(searchGroupOfflineList.get(i), GroupOfflineDTO.class));
        }

        return searchGroupOfflineDTOList;
    }

    public List<GroupOnlineDTO> searchGroupOnlineList(String category, String title, String country) {
        List<Group> groupOnlineList = groupRepository.searchGroupOnlineList(category, title, country);

        List<GroupOnlineDTO> groupOnlineDTO = new ArrayList<>();

        for (int i = 0; i < groupOnlineList.size(); i++) {
            groupOnlineDTO.add(modelMapper.map(groupOnlineList.get(i), GroupOnlineDTO.class));
        }

        return groupOnlineDTO;
    }

    public List<GroupOfflineDTO> searchGroupOfflineList(String category, String title, String country) {
        List<Group> groupOfflineList = groupRepository.searchGroupOfflineList(category, title, country);

        List<GroupOfflineDTO> groupOfflineDTO = new ArrayList<>();

        for (int i = 0; i < groupOfflineList.size(); i++) {
            groupOfflineDTO.add(modelMapper.map(groupOfflineList.get(i), GroupOfflineDTO.class));
        }

        return groupOfflineDTO;
    }

    public String emptyToNull(String param) {
        return (param == null || param.trim().isEmpty()) ? null : param;
    }
}

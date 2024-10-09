package org.community.moyoyoung.kimyong91.service;

import org.community.moyoyoung.dto.GroupOfflineDTO;
import org.community.moyoyoung.dto.GroupOnlineDTO;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GroupListService {

    List<GroupOnlineDTO> getGroupOnlineList();

    List<GroupOfflineDTO> getGroupOfflineList();
}

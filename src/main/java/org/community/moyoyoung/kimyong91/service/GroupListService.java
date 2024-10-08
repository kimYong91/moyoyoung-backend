package org.community.moyoyoung.kimyong91.service;

import org.community.moyoyoung.dto.GroupOfflineListDTO;
import org.community.moyoyoung.dto.GroupOnlineListDTO;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GroupListService {

    List<GroupOnlineListDTO> getGroupOnlineList();

    List<GroupOfflineListDTO> getGroupOfflineList();
}

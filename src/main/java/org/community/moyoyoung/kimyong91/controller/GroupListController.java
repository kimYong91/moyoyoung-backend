package org.community.moyoyoung.kimyong91.controller;

import lombok.RequiredArgsConstructor;
import org.community.moyoyoung.dto.GroupListResponseDTO;
import org.community.moyoyoung.dto.GroupOfflineListDTO;
import org.community.moyoyoung.dto.GroupOnlineListDTO;
import org.community.moyoyoung.kimyong91.service.GroupListService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/main")
public class GroupListController {

    private final GroupListService groupListService;

    @GetMapping("/")
    public ResponseEntity<GroupListResponseDTO> getGroupList() {
        List<GroupOnlineListDTO> onlineGroups = groupListService.getGroupOnlineList();
        List<GroupOfflineListDTO> offlineGroups = groupListService.getGroupOfflineList();

        GroupListResponseDTO response = new GroupListResponseDTO();
        response.setOnlineGroups(onlineGroups);
        response.setOfflineGroups(offlineGroups);

        return ResponseEntity.ok(response);
    }
}

package org.community.moyoyoung.kimyong91.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.community.moyoyoung.dto.GroupListResponseDTO;
import org.community.moyoyoung.dto.GroupOfflineDTO;
import org.community.moyoyoung.dto.GroupOnlineDTO;
import org.community.moyoyoung.entity.GroupImage;
import org.community.moyoyoung.kimyong91.CustomFileUtil;
import org.community.moyoyoung.kimyong91.service.GroupListService;
import org.community.moyoyoung.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/main")
@Log4j2
public class GroupListController {

    private final GroupListService groupListService;
    @Autowired
    CustomFileUtil customFileUtil;

    @Autowired
    GroupRepository groupRepository;

    @GetMapping("/")
    public ResponseEntity<GroupListResponseDTO> getGroupList() {
        List<GroupOnlineDTO> onlineGroups = groupListService.getGroupOnlineList();
        List<GroupOfflineDTO> offlineGroups = groupListService.getGroupOfflineList();

        GroupListResponseDTO response = new GroupListResponseDTO();
        response.setOnlineGroups(onlineGroups);
        response.setOfflineGroups(offlineGroups);

        return ResponseEntity.ok(response);
    }


    @GetMapping("/getImage/{id}")
    public ResponseEntity<Resource> getImage(@PathVariable(name = "id") Long id) {

        GroupImage groupImage = groupListService.getGroupImage(id);

        return customFileUtil.getImage(groupImage);
    }

}

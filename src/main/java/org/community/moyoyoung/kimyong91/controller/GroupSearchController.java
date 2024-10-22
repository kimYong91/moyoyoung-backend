package org.community.moyoyoung.kimyong91.controller;

import lombok.RequiredArgsConstructor;
import org.community.moyoyoung.dto.GroupListResponseDTO;
import org.community.moyoyoung.dto.GroupOfflineDTO;
import org.community.moyoyoung.dto.GroupOnlineDTO;
import org.community.moyoyoung.dto.GroupSearchDTO;
import org.community.moyoyoung.entity.GroupImage;
import org.community.moyoyoung.kimyong91.CustomFileUtil;
import org.community.moyoyoung.kimyong91.service.GroupListService;
import org.community.moyoyoung.kimyong91.service.GroupSearchService;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/search")
public class GroupSearchController {

    private final GroupSearchService groupSearchService;
    private final GroupListService groupListService;
    private final CustomFileUtil customFileUtil;

    @GetMapping("/categoryGroup")
    public ResponseEntity<GroupListResponseDTO> categoryGroupSearch(@RequestParam("category") String category) {
        List<GroupOnlineDTO> groupOnlineDTOList = groupSearchService.searchCategoryGroupOnlineList(category);
        List<GroupOfflineDTO> groupOfflineDTOList = groupSearchService.searchCategoryGroupOfflineList(category);

        GroupListResponseDTO response = new GroupListResponseDTO();
        response.setOnlineGroups(groupOnlineDTOList);
        response.setOfflineGroups(groupOfflineDTOList);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/group")
    public ResponseEntity<GroupListResponseDTO> groupSearch(@RequestBody GroupSearchDTO groupSearchDTO) {

        String emptyToCategory = groupSearchService.emptyToNull(groupSearchDTO.getCategory());
        String emptyToTitle = groupSearchService.emptyToNull(groupSearchDTO.getTitle());
        String emptyToCountry = groupSearchService.emptyToNull(groupSearchDTO.getCountry());

        List<GroupOnlineDTO> groupOnlineDTOList = groupSearchService.searchGroupOnlineList(emptyToCategory, emptyToTitle, emptyToCountry);
        List<GroupOfflineDTO> groupOfflineDTOList = groupSearchService.searchGroupOfflineList(emptyToCategory, emptyToTitle, emptyToCountry);

        GroupListResponseDTO response = new GroupListResponseDTO();
        response.setOnlineGroups(groupOnlineDTOList);
        response.setOfflineGroups(groupOfflineDTOList);

        return ResponseEntity.ok(response);
    }


    @GetMapping("/getImage/{groupId}")
    public ResponseEntity<Resource> getImage(@PathVariable(name = "groupId") Long groupId) {

        GroupImage groupImage = groupListService.getGroupImage(groupId);

        return customFileUtil.getImage(groupImage);
    }


}

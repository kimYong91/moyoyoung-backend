package org.community.moyoyoung.kimyong91.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

import org.community.moyoyoung.dto.*;
import org.community.moyoyoung.entity.*;
import org.community.moyoyoung.kimyong91.CustomFileUtil;
import org.community.moyoyoung.repository.GroupImageRepository;
import org.community.moyoyoung.repository.GroupRepository;
import org.community.moyoyoung.samgak0.services.AuthService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.*;

// 김용
@Service
@Transactional
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {

    private final ModelMapper modelMapper;
    private final GroupRepository groupRepository;
    private final CustomFileUtil customFileUtil;
    private final GroupUserService groupUserService;
    private final GroupImageRepository groupImageRepository;
    private final AuthService authService;

    @Override
    public Long register(GroupDTO groupDTO) {

        MyUser myUser = authService.getLoginData().orElseThrow();


        List<MultipartFile> files = groupDTO.getFile();

        List<String> uploadFileName = customFileUtil.saveFile(files);

        Group group = modelMapper.map(groupDTO, Group.class);
        group.setCreateDate(LocalDate.now());


        for (int i = 0; i < uploadFileName.size(); i++) {
            String fileName = uploadFileName.get(i);
            String upLoadFileName = groupDTO.getFile().get(i).getOriginalFilename();
            GroupImage groupImage = new GroupImage();

            groupImage.setFileName(fileName);
            groupImage.setCreateDate(LocalDate.now());
            groupImage.setUpLoadFileName(upLoadFileName);
            groupImage.setMimeType(groupDTO.getFile().get(i).getContentType());
            GroupImage savedGroupImage = groupImageRepository.save(groupImage);

            group.setGroupImage(savedGroupImage);

            group.setOwnUser(myUser);
        }


        Group result = groupRepository.save(group);

        groupUserService.groupJoin(result.getId());
        return result.getId();
    }

    @Override
    public GroupDTO getOne(Long groupId) {
        Optional<Group> result = groupRepository.findById(groupId);
        Group group = result.orElseThrow();
        GroupDTO groupDTO = modelMapper.map(group, GroupDTO.class);

        Long imageId = group.getGroupImage().getId();
        customFileUtil.getImage(imageId);

        return groupDTO;
    }


    @Override
    public void modify(GroupDTO groupDTO) {
        Optional<Group> result = groupRepository.findById(groupDTO.getId());
        Group group = result.orElseThrow();

        group.setCheckOnline(groupDTO.isCheckOnline());
        group.setCountry(groupDTO.getCountry());
        group.setCategory(groupDTO.getCategory());
        group.setTitle(groupDTO.getTitle());
        group.setContent(groupDTO.getContent());

        if (groupDTO.getGroupImage() != null) {
            group.setGroupImage(groupDTO.getGroupImage());
            Long imageId = group.getGroupImage().getId();
//            groupImageRepository.deleteById(imageId);
            customFileUtil.removeFile(imageId);
            customFileUtil.saveFile(groupDTO.getFile());
        }



        groupRepository.save(group);
    }

    @Override
    public void updateToRemove(Long groupId) {
        groupRepository.updateToRemove(groupId, true);
        Optional<Group> group = groupRepository.findById(groupId);
        Long imageId = group.orElseThrow().getGroupImage().getId();
        groupImageRepository.updateToDeleteImage(imageId, true);
    }

    @Override
    public void realRemove(Long groupId) {
        Optional<Group> group = groupRepository.findById(groupId);
        if (group.orElseThrow().isDelFlag()){
            groupRepository.deleteById(groupId);
        }
    }


    @Override
    public MeetingDTO getMeeting(Long groupId) {
        Optional<Group> result = groupRepository.findById(groupId);

        if (result.isPresent()) {
            Group group = result.get();
            Meeting meeting = group.getMeeting();

            if (meeting != null) {

                return modelMapper.map(meeting, MeetingDTO.class);
            }
        }

        return MeetingDTO.builder().build();
    }

    @Override
    public List<PostMiniDTO> getPostMiniList(Long groupId) {

        Optional<Group> group = groupRepository.findById(groupId);

        List<PostMiniDTO> dtoList;

        List<Object[]> result = groupRepository.selectList(group.get().getId());

        if (!group.get().isCheckOnline()) {
            dtoList = result.stream()
                    .map(arr -> {
                                Post post = (Post) arr[0];
                                MyUser myUser = (MyUser) arr[1]; // MyUser는 null일 수 있으므로 체크
                                String name = (myUser != null) ? myUser.getName() : "Unknown User"; // null 처리
                                return PostMiniDTO.builder()
                                        .id(post.getId())
                                        .title(post.getTitle())
                                        .createDate(post.getCreateDate())
                                        .name(name)
                                        .build();
                            }
                    )
                    .toList();

        } else {
            dtoList = result.stream().map(
                    arr -> {
                        Post post = (Post) arr[0];
                        MyUser myUser = (MyUser) arr[1]; // null 체크
                        String nickname = (myUser != null) ? myUser.getNickname() : "Anonymous"; // null 처리
                        return PostMiniDTO.builder()
                                .id(post.getId())
                                .title(post.getTitle())
                                .createDate(post.getCreateDate())
                                .userNickname(nickname)
                                .build();
                    }
            ).toList();
        }

        return dtoList;
    }

    @Override
    public GroupDetailDTO getGroupDetail(Long id) {
        GroupDTO groupDTO = getOne(id);
        List<PostMiniDTO> postMiniList = getPostMiniList(id);
        MeetingDTO meetingDTO = getMeeting(id);

        GroupDetailDTO groupDetail = new GroupDetailDTO();
        groupDetail.setGroup(groupDTO);
        groupDetail.setPostMiniList(postMiniList);
        groupDetail.setMeeting(meetingDTO);

        return groupDetail;
    }


    @Override
    public Group getGroup(Long groupId) {

        Group findGroup = groupRepository.findById(groupId)
                .orElseThrow(() -> new EntityNotFoundException("해당 그룹의 아이디를 찾을 수 없습니다. : " + groupId));

        return findGroup;
    }

    @Override
    public userStateDTO getGroupUserState(Long groupId, Long userId) {
        userStateDTO groupUserStateDTO = groupRepository.groupUserState(groupId, userId);
        return groupUserStateDTO;

    }
}

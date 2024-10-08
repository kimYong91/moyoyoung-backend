package org.community.moyoyoung.kimyong91.service;

import lombok.RequiredArgsConstructor;

import org.community.moyoyoung.dto.*;
import org.community.moyoyoung.entity.*;
import org.community.moyoyoung.repository.GroupRepository;
import org.community.moyoyoung.repository.MeetingRepository;
import org.community.moyoyoung.repository.PostRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
// 김용
@Service
@Transactional
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService{

    private final ModelMapper modelMapper;
    private final GroupRepository groupRepository;
    private final MeetingRepository meetingRepository;

    @Override
    public Long register(GroupDTO groupDTO) {
        Group group = modelMapper.map(groupDTO, Group.class);
        group.setCreateDate(LocalDate.now());
        Group result = groupRepository.save(group);
        return result.getId();
    }

    @Override
    public GroupDTO getOne(Long id) {
        Optional<Group> result = groupRepository.findById(id);
        Group group = result.orElseThrow();
        GroupDTO groupDTO = modelMapper.map(group, GroupDTO.class);

        return groupDTO;
    }

    @Override
    public void modify(GroupDTO groupDTO) {
        Optional<Group> result = groupRepository.findById(groupDTO.getId());
        Group group = result.orElseThrow();

        group.setCheckOnline(groupDTO.isCheckOnline());
        group.setCountry(groupDTO.getCountry());
        group.setCategory(groupDTO.getCategory());
        group.setGroupImage(groupDTO.getGroupImage());
        group.setTitle(groupDTO.getTitle());
        group.setContent(groupDTO.getContent());
        group.setGroupImage(groupDTO.getGroupImage());

        groupRepository.save(group);
    }

    @Override
    public void remove(Long id) {
        groupRepository.updateToDelete(id, true);
    }


    @Override
    public MeetingDTO getMeeting(Long id) {
        Optional<Group> result = groupRepository.findById(id);
        Meeting meeting = result.get().getMeeting();
        MeetingDTO meetingDTO = modelMapper.map(meeting, MeetingDTO.class);

        return meetingDTO;
    }

    @Override
    public List<PostMiniDTO> getPostMiniList(Long id) {

        Optional<Group> group = groupRepository.findById(id);

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






}

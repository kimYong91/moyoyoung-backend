package org.community.moyoyoung.kimyong91;

import lombok.RequiredArgsConstructor;
import org.community.moyoyoung.dto.GroupDTO;
import org.community.moyoyoung.entity.Group;
import org.community.moyoyoung.repository.GroupRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService{

    private final ModelMapper modelMapper;
    private final GroupRepository groupRepository;

    @Override
    public Long register(GroupDTO groupDTO) {
        Group group = modelMapper.map(groupDTO, Group.class);
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
        group.setContent(groupDTO.getContents());
        group.setGroupImage(groupDTO.getGroupImage());

        groupRepository.save(group);
    }

    @Override
    public void remove(Long id) {
        groupRepository.updateToDelete(id, true);
    }
}

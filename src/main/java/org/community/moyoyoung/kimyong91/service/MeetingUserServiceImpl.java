package org.community.moyoyoung.kimyong91.service;

import lombok.RequiredArgsConstructor;
import org.community.moyoyoung.dto.MeetingUserDTO;
import org.community.moyoyoung.entity.MeetingUser;
import org.community.moyoyoung.repository.MeetingUserRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MeetingUserServiceImpl implements MeetingUserService{


    private final MeetingUserRepository meetingUserRepository;
    private final ModelMapper modelMapper;


    @Override
    public Long register(MeetingUserDTO meetingUserDTO) {
        MeetingUser meetingUser = modelMapper.map(meetingUserDTO, MeetingUser.class);

        MeetingUser result = meetingUserRepository.save(meetingUser);

        return result.getId();
    }

    @Override
    public void remove(Long id) {
        meetingUserRepository.deleteById(id);
    }

    @Override
    public List<MeetingUserDTO> getListAll() {

        List<MeetingUser> all = meetingUserRepository.findAll();

        List<MeetingUserDTO> map = modelMapper.map(all, new TypeToken<List<MeetingUserDTO>>() {}.getType());
        return map;
    }
}

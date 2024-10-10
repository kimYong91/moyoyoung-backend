package org.community.moyoyoung.kimyong91.service;

import lombok.RequiredArgsConstructor;
import org.community.moyoyoung.dto.MeetingUserDTO;
import org.community.moyoyoung.entity.Meeting;
import org.community.moyoyoung.entity.MeetingUser;
import org.community.moyoyoung.entity.MyUser;
import org.community.moyoyoung.repository.MeetingRepository;
import org.community.moyoyoung.repository.MeetingUserRepository;
import org.community.moyoyoung.repository.MyUserRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

// 김용
@Service
@Transactional
@RequiredArgsConstructor
public class MeetingUserServiceImpl implements MeetingUserService{


    private final MeetingUserRepository meetingUserRepository;
    private final MyUserRepository myUserRepository;
    private final MeetingRepository meetingRepository;
    private final ModelMapper modelMapper;


//    @Override
//    public Long join(MeetingUserDTO meetingUserDTO) {
//        MeetingUser meetingUser = modelMapper.map(meetingUserDTO, MeetingUser.class);
//
//        meetingUser.setMeeting(meetingUserDTO.getMeeting());
//        meetingUser.setMyUsers(meetingUserDTO.getUsers());
//
//        MeetingUser result = meetingUserRepository.save(meetingUser);
//
//        return result.getId();
//    }

    @Override
    public MeetingUser join(Long id, Long userId) {
        Meeting meeting = meetingRepository.findById(id).orElseThrow();
        MyUser myUser = myUserRepository.findById(userId).orElseThrow();

        MeetingUser meetingUser = MeetingUser.builder()
                .myUser(myUser)
                .meeting(meeting)
                .build();

        meetingUserRepository.save(meetingUser);


        return meetingUser;
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

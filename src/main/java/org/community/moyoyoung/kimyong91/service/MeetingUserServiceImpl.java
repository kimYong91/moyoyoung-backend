package org.community.moyoyoung.kimyong91.service;

import lombok.RequiredArgsConstructor;
import org.community.moyoyoung.dto.MeetingUserDTO;
import org.community.moyoyoung.entity.Meeting;
import org.community.moyoyoung.entity.MeetingUser;
import org.community.moyoyoung.entity.MyUser;
import org.community.moyoyoung.repository.MeetingRepository;
import org.community.moyoyoung.repository.MeetingUserRepository;
import org.community.moyoyoung.repository.MyUserRepository;
import org.community.moyoyoung.samgak0.services.AuthService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

// 김용
@Service
@Transactional
@RequiredArgsConstructor
public class MeetingUserServiceImpl implements MeetingUserService{


    private final MeetingUserRepository meetingUserRepository;
    private final MyUserRepository myUserRepository;
    private final MeetingRepository meetingRepository;
    private final ModelMapper modelMapper;
    private final AuthService authService;



    @Override
    public MeetingUserDTO meetingJoin(Long meetingId) {
        Optional<MyUser> myUserInfo = authService.getLoginData();
        Long userId = myUserInfo.get().getId();
        Meeting meeting = meetingRepository.findById(meetingId).orElseThrow();

        MyUser myUser = myUserRepository.findById(userId).orElseThrow();

        MeetingUser meetingUser = MeetingUser.builder()
                .myUser(myUser)
                .meeting(meeting)
                .build();

        meetingUserRepository.save(meetingUser);

        MeetingUserDTO MeetingUserDTO = modelMapper.map(meetingUser, MeetingUserDTO.class);

        return MeetingUserDTO;
    }

    @Override // 정기 모임 탈퇴
    public void meetingUserSecession() {

        Optional<MyUser> myUser = authService.getLoginData();
        meetingUserRepository.deleteById(myUser.orElseThrow().getId());
    }

    @Override
    public List<MeetingUserDTO> getMeetingUserListAll() {

        List<MeetingUser> all = meetingUserRepository.findAll();

        List<MeetingUserDTO> map = modelMapper.map(all, new TypeToken<List<MeetingUserDTO>>() {}.getType());
        return map;
    }
}

package org.community.moyoyoung.kimyong91.service;

import lombok.RequiredArgsConstructor;
import org.community.moyoyoung.dto.MeetingDTO;
import org.community.moyoyoung.entity.Group;
import org.community.moyoyoung.entity.Meeting;
import org.community.moyoyoung.repository.GroupRepository;
import org.community.moyoyoung.repository.MeetingRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;
// 김용
@Service
@Transactional
@RequiredArgsConstructor
public class MeetingServiceImpl implements MeetingService{

    private final ModelMapper modelMapper;
    private final MeetingRepository meetingRepository;
    private final GroupRepository groupRepository;

    @Override
    public MeetingDTO get(Long id) {
        Optional<Meeting> result = meetingRepository.findById(id);
        Meeting meeting = result.orElseThrow();
        MeetingDTO meetingDTO = modelMapper.map(meeting, MeetingDTO.class);

        return meetingDTO;
    }




//    @Override
//    public Long register(MeetingDTO meetingDTO) {
//        Meeting meeting = modelMapper.map(meetingDTO, Meeting.class);
//        meeting.setCreateDate(LocalDate.now());
//        Meeting result = meetingRepository.save(meeting);
//        return result.getId();
//    }

    @Override
    public Long register(MeetingDTO meetingDTO) {
        Meeting meeting = modelMapper.map(meetingDTO, Meeting.class);
        meeting.setCreateDate(LocalDate.now());

        Group group = groupRepository.findById(meetingDTO.getGroupId()).orElseThrow();
        meeting.setGroup(group);

        Meeting result = meetingRepository.save(meeting);
        return result.getId();
    }





    @Override
    public void modify(MeetingDTO meetingDTO) {
        Optional<Meeting> meeting = meetingRepository.findById(meetingDTO.getId());

        Meeting result = meeting.orElseThrow();

        result.setTitle(meetingDTO.getTitle());
        result.setContent(meetingDTO.getContent());
        result.setMeetingDate(meetingDTO.getMeetingDate());

        meetingRepository.save(result);
    }

    @Override
    public void remove(Long id) {
        meetingRepository.deleteById(id);
    }
}

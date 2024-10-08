package org.community.moyoyoung;

import org.community.moyoyoung.dto.GroupDTO;
import org.community.moyoyoung.dto.GroupImageDTO;
import org.community.moyoyoung.dto.MeetingDTO;
import org.community.moyoyoung.dto.MyUserDTO;
import org.community.moyoyoung.entity.Group;
import org.community.moyoyoung.entity.GroupImage;
import org.community.moyoyoung.entity.Meeting;
import org.community.moyoyoung.entity.MyUser;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 0

@Configuration
public class MapperConfig {

    @Bean
    public ModelMapper getMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE)
                .setMatchingStrategy(MatchingStrategies.STRICT);


        modelMapper.typeMap(GroupDTO.class, Group.class).addMappings(mapper -> {
            mapper.skip(Group::setMeeting);
            mapper.skip(Group::setGroupImage);
            mapper.skip(Group::setMember);
            mapper.skip(Group::setOwnUser);
            mapper.skip(Group::setPostList);
        });


        return modelMapper;
    }
}
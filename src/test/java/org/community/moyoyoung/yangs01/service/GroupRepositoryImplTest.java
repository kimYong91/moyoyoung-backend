package org.community.moyoyoung.yangs01.service;

import lombok.extern.log4j.Log4j2;
import org.community.moyoyoung.entity.Group;
import org.community.moyoyoung.kimyong91.service.GroupService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Log4j2
@Transactional
class GroupRepositoryImplTest {

    @Autowired
    private GroupService groupService;

    @Test
    @DisplayName("getGroup 테스트")
    void testGetGroup(){
        Long groupId = 1L;

        Group find = groupService.getGroup(groupId);

        log.info("find : " + find);
        log.info("find.getPostList" + find.getPostList());
    }


}
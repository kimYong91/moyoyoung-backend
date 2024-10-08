package org.community.moyoyoung.kimyong91.service;

import org.community.moyoyoung.dto.GroupOfflineListDTO;
import org.community.moyoyoung.dto.GroupOnlineListDTO;
import org.community.moyoyoung.entity.Group;
import org.community.moyoyoung.entity.MyUser;
import org.community.moyoyoung.repository.GroupRepository;
import org.community.moyoyoung.repository.MyUserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Slf4j
@SpringBootTest
class GroupListServiceImplTest {

    @Autowired
    private GroupListService groupListService;

    @Autowired
    MyUserRepository myUserRepository;
    @Autowired
    GroupRepository groupRepository;

    @Test
    public void 회원추가() {
        MyUser mu = new MyUser();
        mu.setUsername("bbbb");
        mu.setPassword("0000");
        mu.setNickname("b0");
        mu.setName("나");
        mu.setPhoneNumber("01012345679");
        mu.setDisabled(false);

        myUserRepository.save(mu);
    }

//    @Test
//    public void 그룹추가() {
//        Group g = new Group();
//        g.setCheckOnline(false);
//        g.setCountry("한국");
//        g.setCategory("운동");
//        g.setTitle("제목");
//        g.setContent("내용");
//        g.setCreateDate(LocalDate.now());
//        MyUser user = new MyUser();
//        user.setId(1L);
//        g.setOwnUser(user);
//
//        groupRepository.save(g);
//    }

    @Test
    @Transactional
    public void 그룹참여() {
        Optional<Group> opt = groupRepository.findById(1L);
        Group g = opt.get();

        MyUser u = new MyUser();
        u.setId(1L);

        List<MyUser> ul = g.getMember();
        ul.add(u);

        g.setMember(ul);

        groupRepository.save(g);
    }

    @Test
    @Transactional
    public void 그룹조회() {
        List<Group> list = groupRepository.getGroupOnlineList();
        System.out.println(list);
    }

    @Test
    @Transactional
    public void 온라인그룹목록조회() {
        List<GroupOnlineListDTO> groupOnlineList = groupListService.getGroupOnlineList();
        System.out.println(groupOnlineList);
    }

    @Test
    @Transactional
    public void 오프라인그룹목록조회() {
        List<GroupOfflineListDTO> groupOfflineList = groupListService.getGroupOfflineList();
        System.out.println(groupOfflineList);
    }
}
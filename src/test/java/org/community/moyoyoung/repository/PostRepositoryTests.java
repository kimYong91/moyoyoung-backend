package org.community.moyoyoung.repository;

import lombok.extern.log4j.Log4j2;
import org.community.moyoyoung.entity.MyUser;
import org.community.moyoyoung.entity.Post;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import java.util.List;
import java.util.Optional;


@SpringBootTest
@Log4j2
class PostRepositoryTests {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private MyUserRepository myUserRepository;

    @Test
    public void testInsert() {

        for (int i = 1; i <= 10; i++) {

            // MyUser myUser = MyUser.builder()
            //         .username("user__" + i) // username에 값을 할당
            //         .name("실명" + i)
            //         .nickname("닉네임__" + i)
            //         .password("password")
            //         .phoneNumber("1234567890")
            //         .disabled(false) // disabled 필드에 false 설정
            //         .build();

            MyUser myUser = new MyUser();
            myUser.setUsername("user__" + i);
            myUser.setName("실명" + i);
            myUser.setNickname("닉네임__" + i);
            myUser.setPassword("password");
            myUser.setPhoneNumber("1234567890");
            myUser.setDisabled(false);


            MyUser savedUser =  myUserRepository.save(myUser);

            // 저장이 성공적으로 이루어졌는지 확인
            if (savedUser.getId() != null) {
                System.out.println("MyUser 저장 성공: " + savedUser.getNickname() + " (ID: " + savedUser.getId() + ")");
            } else {
                System.out.println("MyUser 저장 실패: " + myUser.getNickname());
            }

            Post post = Post.builder()
                    .title("Title..." + i)
                    .createDate(LocalDateTime.of(2024, 10, 7, 18, 01, 00))
                    .content("게시글 내용")
                    .myUser(myUser) // MyUser를 Post와 연결
                    .build();

            postRepository.save(post);
        }

//        // 게시글을 조회하여 사용자 이름 검증
//        List<Post> posts = postRepository.findAll();
//        for (Post post : posts) {
//            PostDTO postDTO = new PostDTO();
//            postDTO.setMyUser(post.getMyUser());
//            postDTO.setCheckOnline(post.isCheckOnline()); // 온라인 상태 설정
//
//            // 사용자 이름 검증
//            String expectedUserName = post.isCheckOnline() ? post.getMyUser().getNickname() : post.getMyUser().getName();
//            assertEquals(expectedUserName, postDTO.getUserName());
//        }
    }
        @Test
        public void testRead () {

            //존재하는 번호로 확인
            Long id = 33L;

            java.util.Optional<Post> result = postRepository.findById(id);

            Post post = result.orElseThrow();

            log.info(post);
        }


    @Test
    public void testModify() {

        Long id = 33L;

        java.util.Optional<Post> result = postRepository.findById(id); //java.util 패키지의 Optional

        Post post = result.orElseThrow();
        post.changeTitle("Modified 33...");
        post.changeModifiedDate(LocalDateTime.now());

        postRepository.save(post);
    }

    @Test
    public void testDelete() {
        Long id = 1L;

        postRepository.deleteById(id);

    }

    @Transactional
    @Test
        public void testPaging () {

            Pageable pageable = PageRequest.of(0, 10, Sort.by("id").descending());

            Page<Post> result = postRepository.findAll(pageable);

            log.info(result.getTotalElements());

            result.getContent().stream().forEach(post -> log.info(post));

        }

        @Test
    void getListTest(){
        List<Post> postList = postRepository.findAll();
        }

        @Test
     void getPost(){
        Long id = 1L;
        Optional<Post> post =  postRepository.findById(id);
        Post post1 = post.orElseThrow();
     }
    }



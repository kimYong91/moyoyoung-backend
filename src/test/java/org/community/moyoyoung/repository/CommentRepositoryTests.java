package org.community.moyoyoung.repository;

import lombok.extern.log4j.Log4j2;
import org.community.moyoyoung.dto.CommentDTO;
import org.community.moyoyoung.entity.Comment;
import org.community.moyoyoung.entity.MyUser;
import org.community.moyoyoung.entity.Post;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Log4j2
class CommentRepositoryTests {

    @Autowired
    private CommentRepository commentRepository;


    @Autowired
    private PostRepository postRepository;

    @Autowired
    private MyUserRepository myUserRepository;

    private MyUser user;
    private Post post;


    @Test
    public void setup() {
        // 필요한 사용자 및 게시글 설정
        user = new MyUser();
        user.setUsername("testuser");
        user.setNickname("테스트 닉네임");
        user.setName("테스트 실명");
        user.setDisabled(false);
        user.setPassword("testpassword");
        user.setPhoneNumber("01012345678");
        user = myUserRepository.save(user); // 사용자 저장

        post = new Post();
        post.setTitle("테스트 게시글");
        post.setContent("게시글 내용");
        post = postRepository.save(post); // 게시글 저장
    }

    @Test
    public void testCommentAuthorOfflineShowsNickname() {
//        // Given
//        Comment comment = new Comment();
//        comment.setCheckOnline(false); // 온라인 상태 설정
//        comment.setContent("오프라인 테스트 댓글.");
//        comment.setMyUser(user);
//        comment.setCreateDate(LocalDateTime.now());
//        comment.setId(1L); // 포스트 ID 설정
//
//        // When
//        Comment savedComment = commentRepository.save(comment);
//
//        // Then
//        assertThat(savedComment).isNotNull();
//        assertThat(savedComment.getId()).isGreaterThan(0);
//        assertThat(savedComment.getContent()).isEqualTo("오프라인 테스트 댓글.");
//        assertThat(savedComment.getMyUser()).isEqualTo(user);
//        assertThat(savedComment.getId()).isEqualTo(1L);
//        assertThat(savedComment.isCheckOnline()).isFalse(); // 오프라인 상태 확인
//        assertThat(savedComment.getCreateDate()).isNotNull();
//        assertThat(savedComment.getName()).isEqualTo(user.getNickname());

        // Given: 댓글 생성 및 저장
        Comment comment = new Comment();
        comment.setContent("오프라인 상태 댓글");
        comment.setMyUser(user); // MyUser 설정
        comment.setPost(post); // 게시글 설정
        comment.setCheckOnline(false);  // 오프라인 설정
        comment.setId(1L); // 포스트 ID 설정

        Comment savedComment = commentRepository.save(comment);
        // When: CommentDTO 생성
        CommentDTO commentDTO = new CommentDTO(savedComment);

        // Then: 사용자 닉네임 확인
        String userName = commentDTO.getMyUser().getNickname();
        assertThat(userName).isEqualTo("테스트 닉네임");
    }

    @Test
    public void testCommentAuthorOnlineShowsRealName() {
        // Given
        Comment comment = new Comment();
        comment.setCheckOnline(true); // 온라인 상태 설정
        comment.setContent("온라인 테스트 댓글.");
        comment.setMyUser(user);
        comment.setCreateDate(LocalDateTime.now());
        comment.setId(1L); // 포스트 ID 설정

        // When
        Comment savedComment = commentRepository.save(comment);

        // Then
        assertThat(savedComment).isNotNull();
        assertThat(savedComment.getId()).isGreaterThan(0);
        assertThat(savedComment.getContent()).isEqualTo("온라인 테스트 댓글.");
        assertThat(savedComment.getMyUser()).isEqualTo(user);
        assertThat(savedComment.getId()).isEqualTo(1L);
        assertThat(savedComment.isCheckOnline()).isTrue(); // 온라인 상태 확인
        assertThat(savedComment.getCreateDate()).isNotNull();
        assertThat(savedComment.getName()).isEqualTo(user.getName()); // name 필드 검증


//        MyUser currentUser = new MyUser();
//        currentUser.setUsername("testUsername");
//        currentUser.setName("테스트 실명");  // 실명 설정
//        currentUser.setNickname("테스트 닉네임2"); // 닉네임 설정
//        currentUser.setDisabled(false); // disabled 값 설정
//        currentUser.setPassword("testpassword");
//        currentUser.setPhoneNumber("01012345678");
//        MyUser savedUser = myUserRepository.save(currentUser); // MyUser를 먼저 저장
//
//        Comment comment = new Comment();
//        comment.setContent("온라인 상태 댓글");
//        comment.setMyUser(currentUser);
//        comment.setPost(post);
//        comment.setCreateDate(LocalDateTime.now());
//        comment.setCheckOnline(true);  // 온라인 설정
//        Comment savedComment = commentRepository.save(comment);
//
//        CommentDTO commentDTO = new CommentDTO(savedComment);
//        String authorName = commentDTO.getMyUser().getName();
//
//        assertThat(authorName).isEqualTo("테스트 실명");
    }

}
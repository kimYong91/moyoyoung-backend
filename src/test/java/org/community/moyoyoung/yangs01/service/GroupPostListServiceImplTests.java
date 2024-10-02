package org.community.moyoyoung.yangs01.service;

import org.community.moyoyoung.dto.PostDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
public class GroupPostListServiceImplTests {

    @Autowired
    private GroupPostListService groupPostListService;

    @Test
    @DisplayName("게시글 등록 서비스 호출 테스트")
    public void testRegister() {

        // PostDTO 객체 생성
        PostDTO postDTO = PostDTO.builder()
                .title("게시글 테스트")
                .content("게시글 내용")
                .id(1L)
                .dueDate(LocalDateTime.of(2024, 10, 1, 0, 0))
                .build();

    }
}

package org.community.moyoyoung.yangs01;


import lombok.extern.log4j.Log4j2;
import org.community.moyoyoung.entity.Post;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
@Log4j2
public class PostListServiceImplTests {
    @Autowired
    private org.community.moyoyoung.yangs01.service.PostListService postListService;











//
//    @Test
//    public void testRegister() {
//
//        // 오프라인 상태: 닉네임 사용
//        PostListDTO offlinePostDTO = PostListDTO.builder()
//                .title("오프라인 작성 테스트")
//                .nickName("offlineUser")
//                .dueDate(LocalDate.from(LocalDateTime.of(2024, 10, 10, 10, 37, 11)))
//                .build();
//
//        Long offlineId = PostListService.register(offlinePostDTO);
//        log.info("Offline id: " + offlineId);
//
//        // 온라인 상태: 실명 사용
//        PostListDTO onlinePostDTO = PostListDTO.builder()
//                .title("온라인 작성 테스트")
//                .name("Online User")
//                .dueDate(LocalDate.of(2023, 10, 11))
//                .build();
//
//        Long onlineId = PostListService.register(onlinePostDTO);
//        log.info("Online id: " + onlineId);
//    }
}



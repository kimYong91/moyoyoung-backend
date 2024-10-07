package org.community.moyoyoung.repository;

import lombok.extern.log4j.Log4j2;
import org.community.moyoyoung.entity.Post;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Log4j2
class PostRepositoryTests {

    @Autowired
    private PostRepository postRepository;

    @Test
    public void testInsert() {

        for (int i = 1; i <= 50; i++) {
            Post post = Post.builder()
                    .title("Title..." + i)
                    .createDate(LocalDateTime.of(2024, 10, 7, 18, 01, 00))
                    .content("게시글 내용")
                    .nickName("닉네임" + i)
                    .build();

            postRepository.save(post);
        }
    }
        @Transactional
        @Test
        public void testPaging() {

            Pageable pageable = PageRequest.of(0,10, Sort.by("id").descending());

            Page<Post> result = postRepository.findAll(pageable);

            log.info(result.getTotalElements());

            result.getContent().stream().forEach(post -> log.info(post));

        }

    }

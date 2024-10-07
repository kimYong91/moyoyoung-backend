package org.community.moyoyoung.repository;

import org.community.moyoyoung.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    // 특정 게시글의 댓글 목록을 가져오는 메서드
    List<Comment> findByPostId(Long postId);
}

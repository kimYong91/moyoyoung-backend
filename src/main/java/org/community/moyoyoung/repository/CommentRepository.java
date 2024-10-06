package org.community.moyoyoung.repository;

import org.community.moyoyoung.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}

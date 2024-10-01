package org.community.moyoyoung.repository;

import org.community.moyoyoung.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

}

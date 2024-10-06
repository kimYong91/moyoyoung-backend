package org.community.moyoyoung.repository;

import org.community.moyoyoung.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PostDetailRepository extends JpaRepository<Post, Long> {
    @Query("select p, mu from Post p left join p.myUser mu where p.id = :id and p.delFlag = false")
    Optional<Object[]> findPostDetailById(@Param("id") Long id);

}

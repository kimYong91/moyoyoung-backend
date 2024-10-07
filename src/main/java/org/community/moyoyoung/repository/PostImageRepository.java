package org.community.moyoyoung.repository;

import org.community.moyoyoung.entity.PostImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostImageRepository extends JpaRepository<PostImage, Long> {
    // 필요한 경우 추가적인 쿼리 메소드 정의 가능
    // 예: 특정 게시글에 연결된 이미지를 조회하는 메소드
    List<PostImage> findByPostId(Long postId);
}

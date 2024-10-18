package org.community.moyoyoung.repository;

import org.community.moyoyoung.entity.GroupImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

// 김용
public interface GroupImageRepository extends JpaRepository<GroupImage, Long> {

    @Modifying
    @Query("update GroupImage gi set gi.delFlag = :flag where gi.id = :id")
    void updateToDeleteImage(@Param("id") Long id, @Param("flag") boolean flag);
}

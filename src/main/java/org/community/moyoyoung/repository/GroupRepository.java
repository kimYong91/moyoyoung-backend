package org.community.moyoyoung.repository;

import org.community.moyoyoung.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface GroupRepository extends JpaRepository<Group, Long> {

    // 실제로 삭제하는것이 아니라 사용자에게만 삭제한것 처럼 보이게 삭제를 하면 정보만 안보이고 DB에는 삭제 되지 않는다.
    @Modifying
    @Query("update Group g set g.delFlag = :flag where g.id = :id")
    void updateToDelete(@Param("id") Long id, @Param("flag") boolean flag);
}

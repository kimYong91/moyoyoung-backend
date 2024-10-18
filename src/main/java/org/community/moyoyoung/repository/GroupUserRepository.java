package org.community.moyoyoung.repository;

import org.community.moyoyoung.entity.GroupUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface GroupUserRepository extends JpaRepository<GroupUser, Long> {

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE tbl_group SET own_user_id = ?1 WHERE own_user_id = ?2 AND id = ?3;")
    void groupOwnUserChange(Long newOwnUserId, Long oldOwnUserId, Long groupId);
}

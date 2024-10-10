package org.community.moyoyoung.repository;

import org.community.moyoyoung.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

// 김용
public interface GroupRepository extends JpaRepository<Group, Long> {

    // 실제로 삭제하는것이 아니라 사용자에게만 삭제한것 처럼 보이게 삭제를 하면 정보만 안보이고 DB에는 삭제 되지 않는다.
    @Modifying
    @Query("update Group g set g.delFlag = :flag where g.id = :id")
    void updateToDelete(@Param("id") Long id, @Param("flag") boolean flag);

    @Query(nativeQuery = true,
            value = "SELECT *," +
                    "       (" +
                    "       SELECT COUNT(1)" +
                    "         FROM tbl_group_member gm" +
                    "        WHERE gm.group_id = tg.id" +
                    "       ) AS cnt" +
                    "  FROM tbl_group tg" +
                    "  WHERE tg.check_online = 1 AND tg.del_flag = 0" +
                    " ORDER BY cnt DESC" +
                    " LIMIT 5"
    )
    List<Group> getGroupOnlineList();

    @Query(nativeQuery = true,
            value = "SELECT *," +
                    "       (" +
                    "       SELECT COUNT(1)" +
                    "         FROM tbl_group_member gm" +
                    "        WHERE gm.group_id = tg.id" +
                    "       ) AS cnt" +
                    "  FROM tbl_group tg" +
                    "  WHERE tg.check_online = 0 AND tg.del_flag = 0" +
                    " ORDER BY cnt DESC" +
                    " LIMIT 5"
    )
    List<Group>  getGroupOfflineList();


    @Query("SELECT p, mu FROM Group g JOIN g.postList p LEFT JOIN p.myUser mu WHERE g.id = :id AND p.delFlag = false ORDER BY p.createDate DESC")
    List<Object[]> selectList(@Param("id") Long id);

}

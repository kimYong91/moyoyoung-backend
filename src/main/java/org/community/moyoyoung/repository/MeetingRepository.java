package org.community.moyoyoung.repository;

import org.community.moyoyoung.entity.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;
// 김용
public interface MeetingRepository extends JpaRepository<Meeting, Long> {
}

package org.community.moyoyoung.repository;

import java.util.Optional;

import org.community.moyoyoung.entity.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
// 김용
// Modified MinU Bak
public interface MyUserRepository extends JpaRepository<MyUser, Long> {
    Optional<MyUser> findByUsername(String username);
    Optional<MyUser> findByNickname(String nickname);
    Optional<MyUser> findByPhoneNumber(String phoneNumber);
    Optional<MyUser> findByNameAndPhoneNumber(String name , String phoneNumber);
}

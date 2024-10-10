package org.community.moyoyoung.repository;

import java.util.Optional;

import org.community.moyoyoung.entity.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
// 김용
// Modified MinU Bak
public interface MyUserRepository extends JpaRepository<MyUser, Long> {
    Optional<MyUser> findByUsernameAndDisabledFalse(String username);
    Optional<MyUser> findByNicknameAndDisabledFalse(String nickname);
    Optional<MyUser> findByPhoneNumberAndDisabledFalse(String phoneNumber);
    Optional<MyUser> findByNameAndPhoneNumberAndDisabledFalse(String name, String phoneNumber);
}

package org.community.moyoyoung.repository;

import org.community.moyoyoung.entity.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyUserRepository extends JpaRepository<MyUser, Long> {
    
}

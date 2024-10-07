package org.community.moyoyoung.samgak0.services;

import java.util.Optional;

import org.community.moyoyoung.entity.MyUser;

//Author : MinU Bak
public interface MyUserService {
    void createUser(MyUser user);
    Optional<MyUser> getUserById(Long id);
    void updateUser(MyUser user);
    boolean deleteUser(Long id);
    boolean validatePassword(String password);
}

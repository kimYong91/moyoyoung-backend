package org.community.moyoyoung.samgak0.services;

import java.util.Optional;

import org.community.moyoyoung.entity.MyUser;

//Author : MinU Bak
public interface MyUserService {
    void createUser(MyUser user);
    Optional<MyUser> getUserById(Long id);
    Optional<MyUser> getUserByUsername(String username);
    Optional<MyUser> getUserByNameAndPhoneNumber(String name, String phoneNumber);
    void updateUser(MyUser user);
    boolean deleteUser(Long id);
    boolean validatePassword(String password);

    boolean checkByNickname(String nickname);
    boolean checkByUsername(String username);
    boolean checkByPhoneNumber(String phoneNumber);
}

package org.community.moyoyoung.samgak0.services;

import java.util.Optional;

import org.community.moyoyoung.dto.MyUserDTO;
import org.community.moyoyoung.entity.MyUser;

//Author : MinU Bak
public interface MyUserService {
    Optional<MyUserDTO> getUserById(Long id);
    Optional<MyUserDTO> getUserByUsername(String username);
    Optional<MyUser> getUserByUsernameReal(String username);
    Optional<MyUserDTO> getUserByPhoneNumber(String phoneNumber);
    Optional<MyUserDTO> getUserByPhoneNumberAndName(String phoneNumber, String name);
    Optional<MyUserDTO> getUserByUsernamePhoneNumberAndName(String username, String phoneNumber, String name);
    
    void createUser(MyUser user);
    Optional<MyUser> findUserByUsername(String username);
    void updateUser(MyUser user);
    boolean deleteUser(Long id);
    boolean validatePassword(String password);

    boolean checkByNickname(String nickname);
    boolean checkByUsername(String username);
    boolean checkByPhoneNumber(String phoneNumber);
}

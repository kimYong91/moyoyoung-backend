package org.community.moyoyoung.samgak0.services;

import org.community.moyoyoung.entity.MyUser;
import java.util.List;

public interface MyUserService {
    void createUser(MyUser user);
    MyUser getUserById(Long id);
    void updateUser(MyUser user);
    void deleteUser(Long id);
    List<MyUser> getAllUsers();
}

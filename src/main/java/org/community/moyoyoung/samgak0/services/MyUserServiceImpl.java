package org.community.moyoyoung.samgak0.services;

import org.community.moyoyoung.entity.MyUser;
import org.community.moyoyoung.repository.MyUserRepository;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MyUserServiceImpl implements MyUserService {

    private final MyUserRepository userRepository;

    @Override
    public void createUser(MyUser user) {
        userRepository.save(user);
    }

    @Override
    public MyUser getUserById(Long id) 
    {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public void updateUser(MyUser user) {
        userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<MyUser> getAllUsers() {
        return userRepository.findAll();
    }
}

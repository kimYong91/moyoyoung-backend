package org.community.moyoyoung.samgak0.services;

import java.util.Optional;
import java.util.regex.Pattern;

import org.community.moyoyoung.entity.MyUser;
import org.community.moyoyoung.repository.MyUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

//Author : MinU Bak
@Slf4j
@Service
@RequiredArgsConstructor
public class MyUserServiceImpl implements MyUserService {
    private static final String PASSWORD_PATTERN = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\W).{8,}$";

    private final MyUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void createUser(MyUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public boolean checkByNickname(String nickname) {
        return userRepository.findByNickname(nickname).isEmpty();
    }

    @Override
    public boolean checkByUsername(String username) {
        return userRepository.findByUsername(username).isEmpty();
    }

    @Override
    public void updateUser(MyUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public boolean deleteUser(Long id) {
        Optional<MyUser> user = userRepository.findById(id);
        
        if (user.isPresent()) {
            MyUser user2 = user.get();

            if (user2.getDisabled() == true) {
                throw new IllegalStateException("The account with ID: " + user2.getId() + " has already been deleted.");
            }

            user2.setDisabled(true);
            userRepository.save(user2);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Optional<MyUser> getUserById(Long id) {
        return userRepository.findById(id);
    }
    
    @Override
    public boolean validatePassword(String password) {
        return Pattern.compile(PASSWORD_PATTERN).matcher(password).matches();
    }
}

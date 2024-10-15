package org.community.moyoyoung.samgak0.services;

import java.util.Optional;
import java.util.regex.Pattern;

import org.community.moyoyoung.entity.MyUser;
import org.community.moyoyoung.dto.MyUserDTO;
import org.community.moyoyoung.repository.MyUserRepository;
import org.modelmapper.ModelMapper;
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
    private final ModelMapper modelMapper;


    @Override
    public void createUser(MyUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public boolean checkByNickname(String nickname) {
        return userRepository.findByNicknameAndDisabledFalse(nickname).isEmpty();
    }

    @Override
    public boolean checkByUsername(String username) {
        return userRepository.findByUsernameAndDisabledFalse(username).isEmpty();
    }

    @Override
    public boolean checkByPhoneNumber(String phoneNumber) {
        return userRepository.findByPhoneNumberAndDisabledFalse(phoneNumber).isEmpty();
    }

    @Override
    public Optional<MyUser> findUserByUsername(String username) {
        return userRepository.findByUsernameAndDisabledFalse(username);
    }

    @Override
    public void updateUser(MyUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setDisabled(false);
        userRepository.save(user);
    }

    @Override
    public boolean deleteUser(Long id) {
        return userRepository.findById(id).map(user -> {
            if (user.getDisabled()) {
                throw new IllegalStateException("The account with ID: " + user.getId() + " has already been deleted.");
            }
            user.setDisabled(true);
            userRepository.save(user);
            return true;
        }).orElse(false);
    }

    @Override
    public Optional<MyUserDTO> getUserById(Long id) {
        return userRepository.findById(id)
                .filter(user -> !user.getDisabled())
                .map(user -> {
                    user.setPassword(null);
                    return modelMapper.map(user, MyUserDTO.class);
                });
    }

    @Override
    public Optional<MyUserDTO> getUserByUsername(String username) {
        return userRepository.findByUsernameAndDisabledFalse(username)
                .map(user -> {
                    user.setPassword(null);
                    return modelMapper.map(user, MyUserDTO.class);
                });
    }

    @Override
    public Optional<MyUser> getUserByUsernameReal(String username) {
        return userRepository.findByUsernameAndDisabledFalse(username);
    }

    @Override
    public Optional<MyUserDTO> getUserByPhoneNumber(String phoneNumber) {
        return userRepository.findByPhoneNumberAndDisabledFalse(phoneNumber)
                .map(user -> {
                    user.setPassword(null);
                    return modelMapper.map(user, MyUserDTO.class);
                });
    }

    @Override
    public Optional<MyUserDTO> getUserByPhoneNumberAndName(String phoneNumber, String name) {
        
        return userRepository.findByPhoneNumberAndDisabledFalse(phoneNumber)
                .filter(user -> !user.getDisabled() && user.getName().equals(name))
                .map(user -> {
                    user.setPassword(null);
                    return modelMapper.map(user, MyUserDTO.class);
                });
    }

    @Override
    public Optional<MyUserDTO> getUserByUsernamePhoneNumberAndName(String username, String phoneNumber, String name) {
        return userRepository.findByUsernameAndDisabledFalse(username)
                .filter(user -> !user.getDisabled() && user.getPhoneNumber().equals(phoneNumber) && user.getName().equals(name))
                .map(user -> {
                    user.setPassword(null);
                    return modelMapper.map(user, MyUserDTO.class);
                });
    }

    @Override
    public boolean validatePassword(String password) {
        return Pattern.compile(PASSWORD_PATTERN).matcher(password).matches();
    }
}

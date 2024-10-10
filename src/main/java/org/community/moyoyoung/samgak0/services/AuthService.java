package org.community.moyoyoung.samgak0.services;

import java.util.Optional;

import org.community.moyoyoung.dto.MyUserDTO;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class AuthService {
    private final MyUserService myUserService;
    
    public Optional<MyUserDTO> getLoginData() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return Optional.empty();
        }

        Optional<MyUserDTO> user = myUserService.getUserByUsername(authentication.getName());
        
        return user;
    }
}

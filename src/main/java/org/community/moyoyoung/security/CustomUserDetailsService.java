package org.community.moyoyoung.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

//Author : MinU Bak
@Slf4j
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username.equals("user")) {
            return org.springframework.security.core.userdetails.User
                    .withUsername(username)
                    .password(new BCryptPasswordEncoder().encode("password"))
                    .authorities("ROLE_USER")
                    .build();
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }
}

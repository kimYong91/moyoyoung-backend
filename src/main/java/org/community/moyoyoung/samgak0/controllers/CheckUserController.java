package org.community.moyoyoung.samgak0.controllers;

import java.util.Optional;

import org.community.moyoyoung.dto.MyUserDTO;
import org.community.moyoyoung.entity.MyUser;
import org.community.moyoyoung.samgak0.services.AuthService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class CheckUserController {

    private final AuthService authService;

    @GetMapping("/my")
    public MyUser my(Authentication authentication) {
        Optional<MyUser> myuser = authService.getLoginData();
        log.info(myuser.toString());
        return myuser.get();
    }
}

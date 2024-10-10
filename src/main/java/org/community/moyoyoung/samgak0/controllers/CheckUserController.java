package org.community.moyoyoung.samgak0.controllers;

import org.community.moyoyoung.dto.MyUserDTO;
import org.community.moyoyoung.entity.MyUser;
import org.community.moyoyoung.samgak0.services.MyUserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
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
    
    private final MyUserService userService;
    private final ModelMapper modelMapper;

    @GetMapping("/check/my")
    public MyUser my(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        MyUserDTO myUserDTO = userService.getUserByUsername(user.getUsername()).orElseThrow();
        return modelMapper.map(myUserDTO, MyUser.class);
    }
}

package org.community.moyoyoung.samgak0.controllers;

import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import org.community.moyoyoung.entity.MyUser;
import org.community.moyoyoung.samgak0.services.MyUserService;

@Slf4j
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class MyUserController {

    private final MyUserService userService;

    @PostMapping
    public ResponseEntity<Long> createUser(@RequestBody MyUser user) {
        userService.createUser(user);
        return ResponseEntity.status(201).body(user.getId());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable("id") Long id) {
        MyUser user = userService.getUserById(id);
        return user != null ? ResponseEntity.ok(user.getId()) : ResponseEntity.status(HttpStatus.NOT_FOUND).body("404 Not Found");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> updateUser(@PathVariable("id") Long id, @RequestBody MyUser user) {
        user.setId(id);
        userService.updateUser(user);
        return ResponseEntity.ok(user.getId());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("204 No Content");
    }

    @GetMapping
    public ResponseEntity<List<MyUser>> getAllUsers() {
        List<MyUser> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }
}

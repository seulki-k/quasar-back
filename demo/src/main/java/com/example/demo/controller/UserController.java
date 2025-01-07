package com.example.demo.controller;


import com.example.demo.dao.Users;
import com.example.demo.service.UserService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<Users> getAllUsers() {
        return userService.getAllUsers(); // 모든 사용자 데이터를 반환
    }

    @PostMapping("/users")
    public void addUser(@RequestBody Users users){
        userService.addUser(users);

    }
    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
    }
}

package com.example.demo.controller;


import com.example.demo.dao.Users;
import com.example.demo.dto.FetchUsers;
import com.example.demo.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name="사용자 정보", description = "사용자 CRUD 구성")
@RestController
public class UserController {
    private final UserService userService;

    
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "사용자 전체 조회")
    @GetMapping("/users")
    public ResponseEntity<List<FetchUsers>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers()); // 모든 사용자 데이터를 반환
    }

    @Operation(summary = "사용자 추가")
    @PostMapping("/users")
    public void addUser(@RequestBody Users users){
        userService.addUser(users);

    }
    @Operation(summary = "사용자 삭제")
    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
    }
}

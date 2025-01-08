package com.example.demo.controller;


import com.example.demo.dao.Users;
import com.example.demo.dto.FetchUsers;
import com.example.demo.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<?> getAllUsers() {
        try {
            return ResponseEntity.ok(userService.getAllUsers()); // 모든 사용자 데이터를 반환
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Operation(summary = "사용자 추가")
    @PostMapping("/users")
    public ResponseEntity<?> addUser(@RequestBody Users users){
        try {
            userService.addUser(users);
            return ResponseEntity.ok("추가 완료");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @Operation(summary = "사용자 변경")
    @PutMapping("/users")
    public ResponseEntity<?> updateUser(@RequestBody FetchUsers fetchUsers){
        try {
            userService.updateUser(fetchUsers);
            return ResponseEntity.ok("변경 완료");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @Operation(summary = "사용자 삭제")
    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id){
        try {
            userService.deleteUser(id);
            return ResponseEntity.ok("삭제 완료");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
}

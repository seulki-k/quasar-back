package com.example.demo.controller;


import com.example.demo.dto.ApiResponse;
import com.example.demo.dto.CreateUserDto;
import com.example.demo.dto.UserDto;
import com.example.demo.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Tag(name = "사용자 정보", description = "사용자 CRUD 구성")
@RestController
@RequestMapping("/api")
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
    public ResponseEntity<ApiResponse<?>> addUser(@Valid @RequestBody CreateUserDto createUserDto) {

        ApiResponse<String> response = new ApiResponse<>();
        try {
            userService.addUser(createUserDto);
            response.setMessage("추가 완료");
            response.setData("유저 등록 성공");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.setError("유저 추가 실패");
            response.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @Operation(summary = "사용자 변경")
    @PutMapping("/users")
    public ResponseEntity<ApiResponse<?>> updateUser(@RequestBody UserDto userDto) {
        ApiResponse<String> response = new ApiResponse<>();
        try {
            userService.updateUser(userDto);
            response.setMessage("변경 완료");
            response.setData("변경 완료");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.setError("유저 변경 실패");
            response.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @Operation(summary = "사용자 삭제")
    @DeleteMapping("/users/{id}")
    public ResponseEntity<ApiResponse<?>> deleteUser(@PathVariable Long id) {
        ApiResponse<String> response = new ApiResponse<>();
        try {
            userService.deleteUser(id);
            response.setMessage("삭제 완료");
            response.setData("삭제 완료");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.setMessage("삭제 실패");
            response.setData("삭제 실패");
            return ResponseEntity.badRequest().body(response);
        }

    }
}

package com.example.demo.users.controller;


import com.example.demo.users.dto.ApiResponse;
import com.example.demo.users.dto.CreateUserDto;
import com.example.demo.users.dto.FetchUserDto;
import com.example.demo.users.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Tag(name = "사용자 정보", description = "사용자 CRUD 구성")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

  private final UserService userService;

  @Operation(summary = "사용자 전체 조회")
  @GetMapping(path = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ApiResponse<List<FetchUserDto>>> getAllUsers() {
    ApiResponse<List<FetchUserDto>> response = new ApiResponse<>();
    response.setMessage("조회 완료");
    response.setData(userService.getAllUsers());
    return ResponseEntity.ok(response); // 모든 사용자 데이터를 반환
  }

  @Operation(summary = "사용자 추가")
  @PostMapping(path = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ApiResponse<?>> addUser(@Valid @RequestBody CreateUserDto createUserDto) {
    ApiResponse<String> response = new ApiResponse<>();
    userService.addUser(createUserDto);
    response.setMessage("추가 완료");
    response.setData("유저 등록 성공");
    return ResponseEntity.ok(response);
  }


  @Operation(summary = "사용자 변경")
  @PutMapping("/users")
  public ResponseEntity<ApiResponse<?>> updateUser(@Valid @RequestBody FetchUserDto fetchUserDto) {
    ApiResponse<String> response = new ApiResponse<>();
    userService.updateUser(fetchUserDto);
    response.setMessage("변경 완료");
    response.setData("변경 완료");
    return ResponseEntity.ok(response);
  }

  @Operation(summary = "사용자 삭제")
  @DeleteMapping("/users/{id}")
  public ResponseEntity<ApiResponse<?>> deleteUser(@PathVariable Long id) {
    ApiResponse<String> response = new ApiResponse<>();
    userService.deleteUser(id);
    response.setMessage("삭제 완료");
    response.setData("삭제 완료");
    return ResponseEntity.ok(response);
  }
}

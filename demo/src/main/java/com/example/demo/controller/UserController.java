package com.example.demo.controller;


import com.example.demo.dto.ApiResponse;
import com.example.demo.dto.CreateUserDto;
import com.example.demo.dto.ParamError;
import com.example.demo.dto.FetchUserDto;
import com.example.demo.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@Tag(name = "사용자 정보", description = "사용자 CRUD 구성")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

  private final UserService userService;

  @Operation(summary = "사용자 전체 조회")
  @GetMapping("/users")
  public ResponseEntity<?> getAllUsers() {
    return ResponseEntity.ok(userService.getAllUsers()); // 모든 사용자 데이터를 반환
  }

  @Operation(summary = "사용자 추가")
  @PostMapping("/users")
  public ResponseEntity<ApiResponse<?>> addUser(@Valid @RequestBody CreateUserDto createUserDto,
      BindingResult bindingResult) {

    if (bindingResult.hasErrors()) {
      List<ParamError> paramErrors = bindingResult.getFieldErrors()
          .stream()
          .map(fieldError -> new ParamError(
              fieldError.getField(),
              fieldError.getRejectedValue(),
              fieldError.getDefaultMessage()
          ))
          .collect(Collectors.toList());

      ApiResponse<Void> response = new ApiResponse<>();
      response.setError("VALIDATION_ERROR");
      response.setMessage("입력값 검증에 실패하였습니다.");
      response.setParamErrors(paramErrors);

      return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    ApiResponse<String> response = new ApiResponse<>();
    userService.addUser(createUserDto);
    response.setMessage("추가 완료");
    response.setData("유저 등록 성공");

    return ResponseEntity.ok(response);
  }


  @Operation(summary = "사용자 변경")
  @PutMapping("/users")
  public ResponseEntity<ApiResponse<?>> updateUser(@Valid @RequestBody FetchUserDto fetchUserDto,
      BindingResult bindingResult) {
    ApiResponse<String> response = new ApiResponse<>();

    if (bindingResult.hasErrors()) {
      List<ParamError> paramErrors = bindingResult.getFieldErrors()
          .stream()
          .map(fieldError -> new ParamError(
              fieldError.getField(),
              fieldError.getRejectedValue(),
              fieldError.getDefaultMessage()
          ))
          .collect(Collectors.toList());

      response.setError("VALIDATION_ERROR");
      response.setMessage("입력값 검증에 실패하였습니다.");
      response.setParamErrors(paramErrors);

      return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

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

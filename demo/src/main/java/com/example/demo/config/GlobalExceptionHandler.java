package com.example.demo.config;

import com.example.demo.dto.ApiResponse;
import com.example.demo.dto.ParamError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

  // Validation 오류 처리
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ApiResponse<Void>> handleValidationException(
      MethodArgumentNotValidException ex) {
    BindingResult bindingResult = ex.getBindingResult();

    // 필드 오류 리스트 생성
    List<ParamError> paramErrors = bindingResult.getFieldErrors()
        .stream()
        .map(this::mapFieldErrorToParamError)
        .collect(Collectors.toList());

    // API 응답 구성
    ApiResponse<Void> response = new ApiResponse<>();
    response.setError("VALIDATION_ERROR");
    response.setMessage("입력값 검증에 실패하였습니다.");
    response.setParamErrors(paramErrors);

    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
  }

  // FieldError를 ParamError로 매핑
  private ParamError mapFieldErrorToParamError(FieldError fieldError) {
    return new ParamError(
        fieldError.getField(),
        fieldError.getRejectedValue(),
        fieldError.getDefaultMessage()
    );
  }
}

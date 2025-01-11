package com.example.demo.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class FetchUserDto {
  @Schema(description = "유저 식별 번호 PK")
  private Long id;
  @NotBlank(message = "이름을 입력하세요")
  @Schema(description = "유저 이름")
  private String name;
  @NotBlank(message = "이메일을 입력하세요")
  @Schema(description = "유저 이메일")
  private String email;
}

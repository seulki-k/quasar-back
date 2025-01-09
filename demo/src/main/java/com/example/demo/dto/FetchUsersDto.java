package com.example.demo.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class FetchUsersDto {

  @Schema(description = "유저 식별 ID")
  private final int id;
  @Schema(description = "유저 이름")
  private final String name;
  @Schema(description = "유저 이메일")
  private final String email;
}

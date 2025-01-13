package com.example.demo.users.dto;

import com.example.demo.users.domain.Users;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public class CreateUserDto {

  @NotBlank(message = "이름을 입력하세요")
  @Schema(description = "유저 이름")
  private String name;
  @NotBlank(message = "이메일을 입력하세요")
  @Schema(description = "유저 이메일")
  private String email;

  public Users toUsers() {
    return Users.builder()
        .name(this.name)
        .email(this.email)
        .build();
  }
}

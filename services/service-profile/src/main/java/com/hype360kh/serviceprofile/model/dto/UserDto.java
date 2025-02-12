package com.hype360kh.serviceprofile.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "User list DTO")
public class UserDto {

  @Schema(description = "User ID", example = "1")
  private Long id;

  @NotBlank(message = "Username is mandatory")
  @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
  @Schema(description = "Username", example = "john-doe@example.com")
  private String username;

  @JsonIgnore
  private String password;

  @NotBlank(message = "Email is mandatory")
  @Email(message = "Email should be valid")
  @Schema(description = "Email address", example = "john-doe@example.com")
  private String email;
}
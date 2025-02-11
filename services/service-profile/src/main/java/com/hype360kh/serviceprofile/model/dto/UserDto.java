package com.hype360kh.serviceprofile.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.Set;
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
  @Schema(description = "Username", example = "john_doe")
  private String username;

  @JsonIgnore
  private String password;

  @NotBlank(message = "Email is mandatory")
  @Email(message = "Email should be valid")
  @Schema(description = "Email address", example = "john_doe@example.com")
  private String email;

  @NotNull(message = "Roles are mandatory")
  @Schema(description = "Roles assigned to the user")
  private Set<String> roles;
}
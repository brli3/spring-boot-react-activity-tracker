package com.brli.myday.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotBlank;

/**
 * @author brandon
 * 2022-09-16
 */

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class LoginDto {
  @NotBlank
  private String username;

  @NotBlank
  private String password;
}

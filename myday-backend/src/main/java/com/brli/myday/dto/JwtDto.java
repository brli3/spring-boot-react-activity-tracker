package com.brli.myday.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author brandon
 * 2022-09-16
 */
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class JwtDto {
  private String accessToken;

  private String type = "Bearer";

  private Long id;

  private String username;

  private String email;

  private List<String> roles;
}

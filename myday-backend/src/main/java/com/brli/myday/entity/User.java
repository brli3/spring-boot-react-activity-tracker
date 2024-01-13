package com.brli.myday.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * @author brandon
 * 2022-09-10
 */
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
@Entity
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank
  @Size(min = 4, max = 20)
  @Column(unique = true)
  private String username;

  @NotBlank
  @Size(min = 4, max = 120)
  private String password;

  @NotBlank
  @Size(max = 50)
  @Email
  @Column(unique = true)
  private String email;

  // Role names split by comma: ROLE_ADMIN, ROLE_USER
  private String roles;

  private boolean isActive;

  private String avatar;

  private LocalDateTime registryTime;
}

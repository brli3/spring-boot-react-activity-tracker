package com.brli.myday.entity;


import lombok.*;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

/**
 * @author brandon
 * 2022-09-10
 */
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
@Entity
public class ExerciseCategory {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @NotNull @Column(unique = true)
  private String name;

  private String avatar;

  private String description;

  private boolean visible = true;
}

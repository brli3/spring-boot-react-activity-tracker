package com.brli.myday.entity;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * @author brandon
 * 2022-09-10
 */
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
@Entity
public class Exercise {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  private String name;

  @NotNull
  private String description;

  @NotNull
  private Integer categoryId;

  private String avatar;

  @NotNull
  private Float met; // Metabolic Equivalent of Task

  private boolean visible = true;
}

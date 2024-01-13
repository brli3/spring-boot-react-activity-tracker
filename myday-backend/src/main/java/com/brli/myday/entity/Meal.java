package com.brli.myday.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * @author brandon
 * 2022-09-23
 */

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
@Entity
public class Meal {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  private String name;

  private String description;

  private String avatar;

  @NotNull
  private Integer categoryId;

  @NotNull
  private Float calPerServing;

  private boolean visible = true;
}

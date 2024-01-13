package com.brli.myday.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

/**
 * @author brandon
 * 2022-09-25
 */
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
@Entity
public class MealRecord {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Long mealId;

  private Float servings;

  private Long userId;

  private LocalDate createdOn;
}

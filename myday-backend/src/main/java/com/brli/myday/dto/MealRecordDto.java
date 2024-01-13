package com.brli.myday.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * @author brandon
 * 2022-09-25
 */
@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class MealRecordDto {
  private Long id;

  private Long mealId;

  // Number of servings
  private Float servings = 1F;

  private LocalDate createdOn;
}

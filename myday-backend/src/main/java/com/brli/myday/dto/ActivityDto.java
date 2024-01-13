package com.brli.myday.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author brandon
 * 2022-09-24
 */
@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class ActivityDto {

  private Long id;

  private Long userId;

  private List<ExerciseRecordDto> exerciseRecords;

  private List<MealRecordDto> mealRecords;

  private Float caloriesIn;

  private Float caloriesOut;

  private String comment;

  private LocalDate createdOn;

}

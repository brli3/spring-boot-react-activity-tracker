package com.brli.myday.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

/**
 * @author brandon
 * 2022-09-24
 */
@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class ActivityUpdateDto {

  private Long id;

  private List<ExerciseRecordDto> exerciseRecords;

  private List<MealRecordDto> mealRecords;

  private String comment;

}

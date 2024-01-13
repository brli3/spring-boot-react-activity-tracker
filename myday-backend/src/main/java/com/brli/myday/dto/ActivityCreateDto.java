package com.brli.myday.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.List;

/**
 * @author brandon
 * 2022-09-24
 */
@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class ActivityCreateDto {

  private List<ExerciseRecordDto> exerciseRecords;

  private List<MealRecordDto> mealRecords;

  private String comment;

  @NotBlank
  private LocalDate createdOn;

}

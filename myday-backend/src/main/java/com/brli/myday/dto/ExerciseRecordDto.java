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
public class ExerciseRecordDto {
  private Long id;

  private Long exerciseId;

  private Float duration;

  private LocalDate createdOn;
}

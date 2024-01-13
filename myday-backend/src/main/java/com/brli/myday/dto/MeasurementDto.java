package com.brli.myday.dto;

import lombok.*;

import java.time.LocalDate;

/**
 * @author brandon
 * 2022-09-25
 */

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class MeasurementDto {

  private Long id;

  private LocalDate createdOn;

  private Long userId;

  private float weight;

}

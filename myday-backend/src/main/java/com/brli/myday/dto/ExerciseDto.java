package com.brli.myday.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * @author brandon
 * 2022-09-15
 */
@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class ExerciseDto {
  private Long id;

  @NotBlank
  private String name;

  @JsonProperty("desc")
  private String description;

  private Integer categoryId;

  private String category;

  private String avatar;

  @NotBlank
  private Float met; // Metabolic Equivalent of Task (to compute burned calories)
}

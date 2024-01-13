package com.brli.myday.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author brandon
 * 2022-09-15
 */
@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class ExerciseCategoryDto {
  private Integer id;

  private String name;

  private String avatar;

  @JsonProperty("desc")
  private String description;
}

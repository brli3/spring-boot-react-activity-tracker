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
public class ExerciseRecord {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Long exerciseId;

  private Float duration;

  private Long userId;

  private LocalDate createdOn;
}

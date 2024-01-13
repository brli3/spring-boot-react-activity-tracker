package com.brli.myday.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author brandon
 * 2022-09-23
 */
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
@Entity
public class Activity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Long userId;

  // Exercise IDs concatenated by '-'
  private String exerciseRecordIds;

  // Meal IDs concatenated by '-'
  private String mealRecordIds;

  private Float caloriesIn;

  private Float caloriesOut;

  private String comment;

  @Column(nullable = false) // 1 activity per user per day
  private LocalDate createdOn;
}

package com.brli.myday.entity;

import lombok.*;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDate;

/**
 * @author brandon
 * 2022-09-25
 */

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
@Entity
public class Measurement {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Long userId;

  private float weight;

  private LocalDate createdOn;
}

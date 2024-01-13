package com.brli.myday.entity;

import lombok.*;

import jakarta.persistence.*;

/**
 * @author brandon
 * 2022-09-16
 */
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
@Entity
public class Role {
  @Id
  private Integer id;

  private String name;
}

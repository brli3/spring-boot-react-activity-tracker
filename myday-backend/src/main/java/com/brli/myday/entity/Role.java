package com.brli.myday.entity;

import lombok.*;

import javax.persistence.*;

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

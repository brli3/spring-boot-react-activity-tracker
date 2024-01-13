package com.brli.myday.enums;

import lombok.Getter;

/**
 * @author brandon
 * 2022-06-27 22:14
 */
@Getter
public enum RoleEnum {

  ROLE_ADMIN(1, "ROLE_ADMIN"),
  ROLE_USER(2, "ROLE_USER");

  RoleEnum(Integer id, String name) {
    this.id = id;
    this.name = name;
  }

  private final Integer id;
  private final String name;
}

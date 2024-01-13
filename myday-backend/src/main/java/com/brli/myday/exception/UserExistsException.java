package com.brli.myday.exception;

import java.io.Serial;

/**
 * @author brandon
 * 2022-09-17
 */
public class UserExistsException extends RuntimeException {
  @Serial
  private static final long serialVersionUID = 1L;

  public UserExistsException(String message) {
    super(message);
  }
}

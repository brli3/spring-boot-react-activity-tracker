package com.brli.myday.exception;

import java.io.Serial;

/**
 * @author brandon
 * 2022-09-10
 */
public class ResourceNotFoundException extends RuntimeException {
  @Serial
  private static final long serialVersionUID = 1L;

  public ResourceNotFoundException(String message) {
    super(message);
  }
}

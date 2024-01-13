package com.brli.myday.exception;

import java.io.Serial;

/**
 * @author brandon
 * 2022-09-21
 */
public class ResourceInUseException extends RuntimeException {
  @Serial
  private static final long serialVersionUID = 1L;

  public ResourceInUseException(String message) {
    super(message);
  }
}

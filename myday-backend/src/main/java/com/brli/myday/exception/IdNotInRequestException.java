package com.brli.myday.exception;

import java.io.Serial;

/**
 * @author brandon
 * 2022-09-15
 */
public class IdNotInRequestException extends RuntimeException{
  @Serial
  private static final long serialVersionUID = 1L;

  public IdNotInRequestException(String message) {
    super(message);
  }
}

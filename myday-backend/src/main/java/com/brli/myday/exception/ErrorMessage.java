package com.brli.myday.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * @author brandon
 * 2022-09-10
 */
@Getter @Builder @AllArgsConstructor
public class ErrorMessage {
  private int status;
  private String timestamp;
  private String message;
  private String description;
}

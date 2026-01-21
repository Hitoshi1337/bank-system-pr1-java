package com.shestak.banksystem.domain.util;

public final class Validate {
  private Validate() {}

  public static <T> T notNull(T value, String field) {
    if (value == null) {
      throw new IllegalArgumentException(field + " cannot be null");
    }
    return value;
  }

  public static String notBlank(String value, String field) {
    if (value == null || value.trim().isEmpty()) {
      throw new IllegalArgumentException(field + " cannot be blank");
    }
    return value.trim();
  }

  public static int positive(int value, String field) {
    if (value <= 0) {
      throw new IllegalArgumentException(field + " must be > 0");
    }
    return value;
  }
}

package com.shestak.banksystem.util;

public class Validate {

  public static void notBlank(String v, String message) {
    if (v == null || v.trim().isEmpty()) {
      throw new IllegalArgumentException(message);
    }
  }

  public static void email(String email) {
    notBlank(email, "Email is required");
    if (!email.contains("@") || !email.contains(".")) {
      throw new IllegalArgumentException("Email is invalid");
    }
  }

  public static void password(String password) {
    notBlank(password, "Password is required");
    if (password.length() < 6) {
      throw new IllegalArgumentException("Password must be at least 6 characters");
    }
  }
}

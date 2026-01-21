package com.shestak.banksystem.domain.entity;

import com.shestak.banksystem.domain.util.Validate;

public class Donor extends BaseEntity<Long> {
  private String name;
  private String email;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = Validate.notBlank(name, "name");
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    String v = Validate.notBlank(email, "email");
    if (!v.contains("@")) {
      throw new IllegalArgumentException("email must contain '@'");
    }
    this.email = v;
  }
}

package com.shestak.banksystem.domain.entity;

import com.shestak.banksystem.domain.util.Validate;

public class RecipientOrg extends BaseEntity<Long> {
  private String name;
  private String address;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = Validate.notBlank(name, "name");
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = Validate.notBlank(address, "address");
  }
}

package com.shestak.banksystem.domain.entity;

import com.shestak.banksystem.domain.util.Validate;

public class Product extends BaseEntity<Long> {
  private String name;
  private String category;
  private String unit;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = Validate.notBlank(name, "name");
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = Validate.notBlank(category, "category");
  }

  public String getUnit() {
    return unit;
  }

  public void setUnit(String unit) {
    this.unit = Validate.notBlank(unit, "unit");
  }
}

package com.shestak.banksystem.domain.entity;

import java.util.Objects;

public abstract class BaseEntity<ID> implements Entity<ID> {
  private ID id;

  @Override
  public ID getId() {
    return id;
  }

  @Override
  public void setId(ID id) {
    if (id == null) {
      throw new IllegalArgumentException("id cannot be null");
    }
    this.id = id;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    BaseEntity<?> that = (BaseEntity<?>) o;
    return Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}

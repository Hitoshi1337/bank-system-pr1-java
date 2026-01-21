package com.shestak.banksystem.domain.entity;

public interface Entity<ID> {
  ID getId();
  void setId(ID id);
}

package com.shestak.banksystem.domain.entity;

import com.shestak.banksystem.domain.util.Validate;

public class Distribution extends BaseEntity<Long> {
  private Long recipientId;
  private String status;

  public Long getRecipientId() {
    return recipientId;
  }

  public void setRecipientId(Long recipientId) {
    this.recipientId = Validate.notNull(recipientId, "recipientId");
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = Validate.notBlank(status, "status");
  }
}

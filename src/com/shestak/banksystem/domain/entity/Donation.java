package com.shestak.banksystem.domain.entity;

import com.shestak.banksystem.domain.util.Validate;

public class Donation extends BaseEntity<Long> {
  private Long donorId;
  private String status;

  public Long getDonorId() {
    return donorId;
  }

  public void setDonorId(Long donorId) {
    this.donorId = Validate.notNull(donorId, "donorId");
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = Validate.notBlank(status, "status");
  }
}

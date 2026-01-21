package com.shestak.banksystem.domain.entity;

import com.shestak.banksystem.domain.util.Validate;

public class DonationItem extends BaseEntity<Long> {
  private Long donationId;
  private Long productId;
  private int quantity;

  public Long getDonationId() {
    return donationId;
  }

  public void setDonationId(Long donationId) {
    this.donationId = Validate.notNull(donationId, "donationId");
  }

  public Long getProductId() {
    return productId;
  }

  public void setProductId(Long productId) {
    this.productId = Validate.notNull(productId, "productId");
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = Validate.positive(quantity, "quantity");
  }
}

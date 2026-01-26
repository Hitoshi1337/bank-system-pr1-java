package com.shestak.banksystem.domain.entity;

import java.util.ArrayList;
import java.util.List;

public class Donation extends BaseEntity implements Entity {
    private Donor donor;
    private final List<DonationItem> items = new ArrayList<>();

    public Donation() {}

    public Donation(Donor donor) { this.donor = donor; }

    public Donor getDonor() { return donor; }
    public void setDonor(Donor donor) { this.donor = donor; }

    public List<DonationItem> getItems() { return items; }

    public void addItem(DonationItem item) { items.add(item); }
}

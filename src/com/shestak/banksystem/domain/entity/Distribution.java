package com.shestak.banksystem.domain.entity;

import java.util.ArrayList;
import java.util.List;

public class Distribution extends BaseEntity implements Entity {
    private RecipientOrg recipientOrg;
    private final List<DonationItem> items = new ArrayList<>();

    public Distribution() {}

    public Distribution(RecipientOrg recipientOrg) { this.recipientOrg = recipientOrg; }

    public RecipientOrg getRecipientOrg() { return recipientOrg; }
    public void setRecipientOrg(RecipientOrg recipientOrg) { this.recipientOrg = recipientOrg; }

    public List<DonationItem> getItems() { return items; }

    public void addItem(DonationItem item) { items.add(item); }
}

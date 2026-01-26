package com.shestak.banksystem.domain.entity;

public class RecipientOrg extends BaseEntity implements Entity {
    private String name;

    public RecipientOrg() {}

    public RecipientOrg(String name) { this.name = name; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}

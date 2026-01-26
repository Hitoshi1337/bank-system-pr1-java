package com.shestak.banksystem.domain.entity;

public class Donor extends BaseEntity implements Entity {
    private String name;

    public Donor() {}

    public Donor(String name) { this.name = name; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}

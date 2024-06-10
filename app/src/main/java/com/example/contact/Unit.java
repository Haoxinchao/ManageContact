package com.example.contact;

public class Unit {
    private long id;
    private String unitCode;
    private String unitName;
    private String email;
    private String website;
    private byte[] logo;
    private String address;
    private String phone;
    private String parentUnitCode;

    public Unit() {}

    public Unit(String unitCode, String unitName, String email, String website, byte[] logo, String address, String phone, String parentUnitCode) {
        this.unitCode = unitCode;
        this.unitName = unitName;
        this.email = email;
        this.website = website;
        this.logo = logo;
        this.address = address;
        this.phone = phone;
        this.parentUnitCode = parentUnitCode;
    }

    // Getters and Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUnitCode() {
        return unitCode;
    }

    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public byte[] getLogo() {
        return logo;
    }

    public void setLogo(byte[] logo) {
        this.logo = logo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getParentUnitCode() {
        return parentUnitCode;
    }

    public void setParentUnitCode(String parentUnitCode) {
        this.parentUnitCode = parentUnitCode;
    }
}

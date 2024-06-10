package com.example.contact;

public class Employee {
    private long id;
    private String employeeId;
    private String name;
    private String position;
    private String email;
    private String phone;
    private byte[] avatar;

    // Constructor mặc định
    public Employee() {}

    // Constructor với các tham số
    public Employee(String employeeId, String name, String position, String email, String phone, byte[] avatar) {
        this.employeeId = employeeId;
        this.name = name;
        this.position = position;
        this.email = email;
        this.phone = phone;
        this.avatar = avatar;
    }

    // Getter và Setter
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public byte[] getAvatar() {
        return avatar;
    }

    public void setAvatar(byte[] avatar) {
        this.avatar = avatar;
    }
}

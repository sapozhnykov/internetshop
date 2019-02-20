package com.sapozhnykov.domain;

public class AuthClient {

    private long id;
    private long userId;
    private String phone;
    private String password;

    public AuthClient(long id, long userId, String phone, String password) {
        this.id = id;
        this.userId = userId;
        this.phone = phone;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

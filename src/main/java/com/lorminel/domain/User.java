package com.lorminel.domain;

public class User {

    private final String username;
    private final String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String username() {
        return username;
    }

    public boolean isPasswordEqual(String password) {
        return this.password.equals(password);
    }
}

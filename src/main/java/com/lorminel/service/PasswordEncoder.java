package com.lorminel.service;

public interface PasswordEncoder {

    String encode(String password);

    String decode(String password);
}

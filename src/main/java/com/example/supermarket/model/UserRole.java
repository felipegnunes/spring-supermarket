package com.example.supermarket.model;

import org.springframework.security.core.GrantedAuthority;

public class UserRole implements GrantedAuthority {

    public static final String ADMIN = "ROLE_ADMIN";
    public static final String CASHIER = "ROLE_CASHIER";

    private String authority;

    public UserRole() {
    }

    public UserRole(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return authority;
    };
}

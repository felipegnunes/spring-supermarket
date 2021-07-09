package com.example.supermarket.service;

import java.util.Set;

import com.example.supermarket.model.User;
import com.example.supermarket.model.UserRole;

public interface AuthService {

    User signup(String username, String email, String password, Set<UserRole> authorities);

    User login(String username, String password);

}

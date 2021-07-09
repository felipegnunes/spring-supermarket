package com.example.supermarket.controller;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

import com.example.supermarket.configuration.security.JwtTokenUtil;
import com.example.supermarket.dto.LoginDto;
import com.example.supermarket.dto.SignupDto;
import com.example.supermarket.model.User;
import com.example.supermarket.model.UserRole;
import com.example.supermarket.service.AuthService;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

    private JwtTokenUtil jwtTokenUtil;
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody @Valid LoginDto request) {

        User user = authService.login(request.getUsername(), request.getPassword());

        return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, jwtTokenUtil.generateAccessToken(user)).body(user);
    }

    @PostMapping("/signup")
    @RolesAllowed(UserRole.ADMIN)
    public User signup(@RequestBody @Valid SignupDto request) {
        Set<UserRole> authorities = new HashSet<>();
        authorities.add(new UserRole(UserRole.CASHIER));

        User user = authService.signup(request.getUsername(), request.getEmail(), request.getPassword(), authorities);

        return user;
    }

}

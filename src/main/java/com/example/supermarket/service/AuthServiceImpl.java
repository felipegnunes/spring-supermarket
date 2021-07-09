package com.example.supermarket.service;

import java.util.Set;

import com.example.supermarket.exception.ConflictException;
import com.example.supermarket.exception.UnauthorizedException;
import com.example.supermarket.model.User;
import com.example.supermarket.model.UserRole;
import com.example.supermarket.repository.UserRepository;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private AuthenticationManager authenticationManager;

    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    public User signup(String username, String email, String password, Set<UserRole> authorities) {
        if (userRepository.findByUsername(username).isPresent()) {
            throw new ConflictException();
        }

        if (userRepository.findByEmail(email).isPresent()) {
            throw new ConflictException();
        }

        String encodedPassword = passwordEncoder.encode(password);
        User newUser = new User(username, email, encodedPassword);
        newUser.setAuthorities(authorities);
        userRepository.save(newUser);

        return newUser;
    }

    public User login(String username, String password) {
        try {
            Authentication authenticate = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(username, password));

            User user = (User) authenticate.getPrincipal();

            return user;
        } catch (BadCredentialsException ex) {
            throw new UnauthorizedException();
        }
    }

}

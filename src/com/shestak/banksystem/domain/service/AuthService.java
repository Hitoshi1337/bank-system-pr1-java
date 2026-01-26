package com.shestak.banksystem.domain.service;

import com.shestak.banksystem.domain.dto.LoginDto;
import com.shestak.banksystem.domain.dto.UserRegistrationDto;
import com.shestak.banksystem.domain.entity.Role;
import com.shestak.banksystem.domain.entity.User;
import com.shestak.banksystem.domain.repository.UserRepository;
import com.shestak.banksystem.util.Validate;

public class AuthService {
    private final UserRepository users;
    private final EmailService email;

    public AuthService(UserRepository users, EmailService email) {
        this.users = users;
        this.email = email;
    }

    public User register(UserRegistrationDto dto) {
        Validate.email(dto.email());
        Validate.password(dto.password());

        if (users.findByEmail(dto.email()).isPresent()) {
            throw new IllegalArgumentException("Email already exists");
        }

        User u = new User(dto.email(), dto.password(), Role.USER);
        users.save(u);

        email.send(u.getEmail(), "Registration", "Welcome to Food Bank System!");
        return u;
    }

    public User login(LoginDto dto) {
        Validate.email(dto.email());
        Validate.password(dto.password());

        User u = users.findByEmail(dto.email())
            .orElseThrow(() -> new IllegalArgumentException("Bad credentials"));

        if (!u.getPassword().equals(dto.password())) {
            throw new IllegalArgumentException("Bad credentials");
        }
        return u;
    }
}

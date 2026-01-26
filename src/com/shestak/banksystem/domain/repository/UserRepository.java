package com.shestak.banksystem.domain.repository;

import com.shestak.banksystem.domain.entity.User;
import java.util.Optional;

public class UserRepository extends InMemoryRepository<User> {
    public Optional<User> findByEmail(String email) {
        return findAll().stream()
                .filter(u -> u.getEmail() != null && u.getEmail().equalsIgnoreCase(email))
                .findFirst();
    }
}

package com.shestak.banksystem.domain.service;

import com.shestak.banksystem.domain.entity.Role;
import com.shestak.banksystem.domain.entity.User;
import com.shestak.banksystem.domain.repository.UserRepository;

import java.util.List;

public class UserService {
  private final UserRepository users;

  public UserService(UserRepository users) {
    this.users = users;
  }

  public List<User> list() {
    return users.findAll();
  }

  public void setRole(Long userId, Role role) {
    User u = users.findById(userId)
        .orElseThrow(() -> new IllegalArgumentException("User not found: " + userId));
    u.setRole(role);
    users.save(u);
  }
}

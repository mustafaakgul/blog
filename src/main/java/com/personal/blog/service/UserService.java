package com.personal.blog.service;

import com.personal.blog.model.User;

import java.util.Optional;

public interface UserService {

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    User save(User user);

    Optional<User> getUserByUsername(String username);
}

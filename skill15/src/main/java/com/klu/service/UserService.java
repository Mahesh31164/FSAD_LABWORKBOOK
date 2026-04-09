package com.klu.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klu.entity.User;
import com.klu.repository.UserRepository;


@Service
public class UserService {

    @Autowired
    private UserRepository repo;

    public User validateUser(String username, String password) {
        User user = repo.findByUsername(username).orElse(null);

        if (user == null || !user.getPassword().equals(password)) {
            throw new RuntimeException("Invalid credentials");
        }

        return user;
    }
}
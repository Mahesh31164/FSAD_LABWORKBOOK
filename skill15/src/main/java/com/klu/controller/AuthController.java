package com.klu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.klu.dto.LoginRequest;
import com.klu.entity.User;
import com.klu.security.JwtUtil;
import com.klu.service.UserService;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class AuthController {

    @Autowired
    private UserService service;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest req) {
        User user = service.validateUser(req.username, req.password);
        return jwtUtil.generateToken(user.getUsername(), user.getRole());
    }
}
package com.klu.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.klu.dto.LoginRequestDTO;
import com.klu.dto.RegisterRequestDTO;
import com.klu.dto.UserResponseDTO;
import com.klu.service.UserService;


@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {

    @Autowired
    private UserService service;

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequestDTO dto) {
        service.register(dto);
        return "User Registered Successfully";
    }

    @PostMapping("/login")
    public UserResponseDTO login(@RequestBody LoginRequestDTO dto) {
        return service.login(dto);
    }

    @GetMapping("/profile/{username}")
    public UserResponseDTO getProfile(@PathVariable String username) {
        return service.getUserByUsername(username);
    }
}
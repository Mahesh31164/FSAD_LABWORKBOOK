package com.klu.service;

import com.klu.dto.LoginRequestDTO;
import com.klu.dto.RegisterRequestDTO;
import com.klu.dto.UserResponseDTO;

public interface UserService {
    void register(RegisterRequestDTO dto);
    UserResponseDTO login(LoginRequestDTO dto);
    UserResponseDTO getUserByUsername(String username);
}
package com.klu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klu.dto.LoginRequestDTO;
import com.klu.dto.RegisterRequestDTO;
import com.klu.dto.UserResponseDTO;
import com.klu.entity.User;
import com.klu.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repo;

    @Override
    public void register(RegisterRequestDTO dto) {
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());// later encrypt
        repo.save(user);
    }

    @Override
    public UserResponseDTO login(LoginRequestDTO dto) {
//    	System.out.println("LOGIN USERNAME = " + dto.getUsername());
        User user = repo.findByUsername(dto.getUsername())
                .orElse(null);

        if (user == null) {
            throw new RuntimeException("INVALID_USERNAME");
        }

        if (!user.getPassword().equals(dto.getPassword())) {
            throw new RuntimeException("INVALID_PASSWORD");
        }

        UserResponseDTO res = new UserResponseDTO();
        res.id = user.getId();
        res.username = user.getUsername();
        res.email = user.getEmail();

        return res;
    }

    @Override
    public UserResponseDTO getUserByUsername(String username) {
        User user = repo.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        UserResponseDTO res = new UserResponseDTO();
        res.id = user.getId();
        res.username = user.getUsername();
        res.email = user.getEmail();

        return res;
    }
}
package com.example.mockproject_112024_democrud_be.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mockproject_112024_democrud_be.entity.User;
import com.example.mockproject_112024_democrud_be.repository.UserRepository;

/**
 * UserService
 * Version: 1.0
 * Date: 11/8/2024
 * Modification Logs
 * DATE AUTHOR DESCRIPTION
 * -------------------------------------
 * 11/8/2024 kiet-kun-afk Create
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> searchUsers(String keywordString) {
        return userRepository.searchByKeyword(keywordString);
    }
}

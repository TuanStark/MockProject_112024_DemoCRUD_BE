package com.example.mockproject_112024_democrud_be.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.mockproject_112024_democrud_be.entity.User;
import com.example.mockproject_112024_democrud_be.service.UserService;

/**
 * UserController
 * Version: 1.0
 * Date: 11/8/2024
 * Modification Logs
 * DATE AUTHOR DESCRIPTION
 * -------------------------------------
 * 11/8/2024 kiet-kun-afk Create
 * 11/8/2024 kiet-kun-afk update
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/search")
    public ResponseEntity<?> searchByKeyword(@RequestParam String keyword) {
        List<User> users = userService.searchUsers(keyword);

        if (users.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(users);
    }

}

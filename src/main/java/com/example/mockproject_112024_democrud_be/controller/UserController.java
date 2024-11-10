package com.example.mockproject_112024_democrud_be.controller;

import java.util.List;

import com.example.mockproject_112024_democrud_be.helper.PageResponse;
import com.example.mockproject_112024_democrud_be.helper.response.ResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/getAll")
    public ResponseObject<PageResponse> getAllCategory(
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @RequestParam(value = "size", required = false) Integer size,
            @RequestParam(value = "limit", required = false) Integer limit) {
        if (size == null) {
            size = Integer.MAX_VALUE;
        }
        PageResponse<User> response = userService.getAllCategory(page, size,limit);
        return ResponseObject.<PageResponse>builder()
                .message("SUCCESS")
                .code(200)
                .data(response)
                .build();
    }
    @DeleteMapping("/{userId}")
    ResponseObject<String> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ResponseObject.<String>builder()
                .message("User has been deleted")
                .code(200)
                .build();
    }
    @GetMapping("/getAllNoPageList")
    public ResponseObject<List<User>> getAllNoPageList() {
        List<User> response =  userService.getAllNoPageList();
        return ResponseObject.<List<User>>builder()
                .message("SUCCESS")
                .code(200)
                .data(response)
                .build();
    }
}

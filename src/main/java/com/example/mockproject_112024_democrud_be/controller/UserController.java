package com.example.mockproject_112024_democrud_be.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import com.example.mockproject_112024_democrud_be.helper.PageResponse;
import com.example.mockproject_112024_democrud_be.helper.response.ResponseObject;
import com.example.mockproject_112024_democrud_be.modelDto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
 * 11/8/2024 kiet-kun-afk Create
 * 11/8/2024 kiet-kun-afk update
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

    @GetMapping("/getAll")
    public ResponseObject<PageResponse> getAllCategory(
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @RequestParam(value = "size", required = false) Integer size,
            @RequestParam(value = "limit", required = false) Integer limit) {
        if (size == null) {
            size = Integer.MAX_VALUE;
        }
        PageResponse<User> response = userService.getAllCategory(page, size, limit);
        return ResponseObject.<PageResponse>builder()
                .message("SUCCESS")
                .code(200)
                .data(response)
                .build();
    }

    @GetMapping("/getAllNoPageList")
    public ResponseObject<List<User>> getAllNoPageList() {
        List<User> response = userService.getAllNoPageList();
        return ResponseObject.<List<User>>builder()
                .message("SUCCESS")
                .code(200)
                .data(response)
                .build();
    }

    @PutMapping("/{id}")
    ResponseEntity<String> update(@PathVariable("id") Long id, @RequestBody UserDto userNew) {
        System.out.println(userNew);
        User userOld = this.userService.findById(id);
        userOld.setName(userNew.getName());
        userOld.setAddress(userNew.getAddress());
        userOld.setEmail(userNew.getEmail());
        userOld.setPhone(userNew.getPhone());
        // System.out.println(userNew.getYearOfBirth());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
        try {
            LocalDateTime date = LocalDate.parse(userNew.getYearOfBirth(), formatter).atStartOfDay();
            System.out.println(date);
            userOld.setYearOfBirth(date);
        } catch (DateTimeParseException e) {
            e.printStackTrace();
        }

        //System.out.println(userOld);
        if (this.userService.update(userOld) != null) {
            return new ResponseEntity<>("thành công", HttpStatus.OK);
        }
        return new ResponseEntity<>("thất bại", HttpStatus.BAD_REQUEST);

    }
}

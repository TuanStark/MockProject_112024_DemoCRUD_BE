package com.example.mockproject_112024_democrud_be.service;

import java.util.List;
import java.util.stream.Collectors;

import com.example.mockproject_112024_democrud_be.helper.PageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    public PageResponse<User> getAllCategory(int page, int size, int limit){
        Sort sort = Sort.by("createdAt").ascending();
        Pageable pageable = PageRequest.of(page-1,size,sort);
        Page<User> pageData = userRepository.findAll(pageable);
        List<User> listUser = pageData.getContent();
        if (limit > 0) {
            listUser = listUser.stream()
                    .limit(limit)
                    .collect(Collectors.toList());
        }
        return PageResponse.<User>builder()
                .currentPage(page)
                .pageSize(size)
                .totalPage(pageData.getTotalPages())
                .totalElement(pageData.getTotalElements())
                .data(listUser)
                .build();
    }
}

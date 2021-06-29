package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.User;
import org.springframework.http.ResponseEntity;


public interface UserService {
    List<User> getAll();
    Optional<User> getById(Integer userId);
    User createUser(User user);
    User updateUser(User userDetails, Integer userId);
    ResponseEntity<?> deleteUser(Integer userId);
}

package com.example.e_management_store_server.controller;

import com.example.e_management_store_server.model.User;
import com.example.e_management_store_server.repository.UserRepository;
import com.example.e_management_store_server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;


    @PostMapping("/login")
    public ResponseEntity<?> login(User user){
        Optional<User> foundUser = userRepository.findByEmail(user.getEmail());
        if(foundUser.isPresent() && foundUser.get().getPassword().equals(user.getPassword())){
            return ResponseEntity.ok(Collections.singletonMap("message", "Login successful"));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
    }

     @PostMapping("/register")
    public ResponseEntity<?> register(User user){
       Optional<User> foundUser = userRepository.findByEmail(user.getEmail());
        if(foundUser.isPresent()){
            return  ResponseEntity.status(HttpStatus.CONFLICT).body("User already exist");
        }
        userService.postUser(user);
        return  ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
     }

}

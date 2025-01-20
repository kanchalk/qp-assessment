package com.example.groceryBookingApi.controller;

import com.example.groceryBookingApi.entity.UserInfo;
import com.example.groceryBookingApi.repository.UserRepository;
import com.example.groceryBookingApi.request.AuthRequest;
import com.example.groceryBookingApi.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AuthRequest user) {
        Authentication authentication=authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
        );
        String token = JwtUtils.generateToken(user.getUsername(),authentication.getAuthorities().toString());
        return ResponseEntity.ok(token);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody AuthRequest user) {
        UserInfo ur=new UserInfo();
        ur.setUsername(user.getUsername());
        ur.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        ur.setRole("USER");  // Set default role as USER
        userRepository.save(ur);
        return ResponseEntity.ok("User registered successfully");
    }
}


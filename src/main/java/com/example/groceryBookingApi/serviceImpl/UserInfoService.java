package com.example.groceryBookingApi.serviceImpl;

import com.example.groceryBookingApi.config.UserInfoDetails;
import com.example.groceryBookingApi.entity.UserInfo;
import com.example.groceryBookingApi.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserInfoService implements UserDetailsService {

    @Autowired
    private final UserRepository repository;

    @Autowired
    private PasswordEncoder encoder;

    public UserInfoService(UserRepository repository) {
        this.repository = repository;
    }

    @Autowired
    private Optional<UserInfo> userDetail;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
         userDetail = repository.findByUsername(username); // Returns Optional<UserInfo>

        return userDetail
                .map(UserInfoDetails::new)  // If user found, map to UserInfoDetails
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));  // If user not found, throw exception
    }

    public String addUser(UserInfo userInfo) {
        userInfo.setPassword(encoder.encode(userInfo.getPassword()));
        repository.save(userInfo);  // Save the user in the database
        return "User Added Successfully";
    }
}

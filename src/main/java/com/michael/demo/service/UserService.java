package com.michael.demo.service;
import com.michael.demo.model.User;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {
    public List <User> getAllUsers();
    public Optional<User> getUserById(Long id);

    ResponseEntity<?> getUserById(Long id, String token);

    public void registerUser(User user);
    public void deleteUser( Long id);
    public void updateUserAddress(Long userId, Long addressId);
}

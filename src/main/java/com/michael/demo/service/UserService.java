package com.michael.demo.service;
import com.michael.demo.dto.UserDto;
import com.michael.demo.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    public List <User> getAllUsers();
    public Optional<User> getUserById(Long id);
    public void registerUser(UserDto user);
    public void deleteUser( Long id);
    public void updateUserAddress(Long userId, Long addressId);
}

package com.michael.demo.service;
import com.michael.demo.dto.UserDto;
import com.michael.demo.errorhandling.EmailAlreadyExistsException;
import com.michael.demo.model.User;
import com.michael.demo.repository.AddressRepository;
import com.michael.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public void registerUser(UserDto userDto) {
        // Check if the email is already in use
        if (userRepository.existsByEmail(userDto.email())) {
            throw new EmailAlreadyExistsException("Email is already in use.");
        }
        User user = new User();
        user.setFullName(userDto.fullName());
        user.setEmail(userDto.email());
        user.setDateOfBirth(userDto.dateOfBirth());
        userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void updateUserAddress(Long userId, Long addressId) {
        var user = userRepository.findById(userId).orElseThrow();
        var address = addressRepository.findById(addressId).orElseThrow();

        user.setAddress(address);

        userRepository.save(user);
        System.out.println(user);
    }



}

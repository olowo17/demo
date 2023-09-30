package com.michael.demo.service;
import com.michael.demo.model.User;
import com.michael.demo.repository.AddressRepository;
import com.michael.demo.repository.UserRepository;
import com.michael.demo.util.JwtToken;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    @Override
    public Optional<User> getUserById(Long id) {
        return Optional.empty();
    }

    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtToken jwtToken;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public ResponseEntity<?> getUserById(Long id, String token) {
        String tokenCheckResult = jwtToken.validateToken(token);

        if (tokenCheckResult.equalsIgnoreCase("valid")) {
            Optional<User> user = userRepository.findById(id);
            if (user.isPresent()) {
                return ResponseEntity.ok(user.get());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized due to: " + tokenCheckResult);
        }
    }



    @Override
    public void registerUser(User user) {
        String password = user.getPassword();
            // Hash the password
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);
        user.setFullName(user.getFullName());
        user.setEmail(user.getEmail());
        user.setDateOfBirth(user.getDateOfBirth());
        user.setPassword(hashedPassword);
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

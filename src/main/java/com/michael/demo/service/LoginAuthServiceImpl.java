package com.michael.demo.service;

import com.michael.demo.model.TokenRequestResponse;
import com.michael.demo.model.User;
import com.michael.demo.repository.UserRepository;
import com.michael.demo.util.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginAuthServiceImpl implements LoginAuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtToken jwtToken;

    public ResponseEntity<Object> generateToken(TokenRequestResponse tokenRequestResponse) {
        Optional<User> userOptional = userRepository.findByEmail(tokenRequestResponse.getUsername());
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

            if (passwordEncoder.matches(tokenRequestResponse.getPassword(), user.getPassword())) {
                String token = jwtToken.generateToken(tokenRequestResponse.getUsername());
                TokenRequestResponse response = new TokenRequestResponse();
                response.setToken(token);
                response.setExpirationTime("60 seconds");
                return ResponseEntity.ok(response);
            }
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body("Invalid Credentials");
    }
}

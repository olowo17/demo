package com.michael.demo.Controller;

import com.michael.demo.model.TokenRequestResponse;
import com.michael.demo.model.User;
import com.michael.demo.repository.UserRepository;
import com.michael.demo.util.JwtToken;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class LoginController {
    @Autowired
    private JwtToken jwtToken;
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<Object> generateToken(@RequestBody TokenRequestResponse tokenRequestResponse){
        Optional<User> userOptional = userRepository.findByEmail(tokenRequestResponse.getUsername());
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (user.getPassword().equals(tokenRequestResponse.getPassword())) {
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

    @PostMapping("/validate-token")
    public ResponseEntity<Object> validateToken(@RequestBody TokenRequestResponse tokenReqRes){
        return ResponseEntity.ok(jwtToken.validateToken(tokenReqRes.getToken()));
    }

}

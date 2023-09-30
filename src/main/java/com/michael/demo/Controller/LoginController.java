package com.michael.demo.Controller;

import com.michael.demo.model.TokenRequestResponse;
import com.michael.demo.model.User;
import com.michael.demo.repository.UserRepository;
import com.michael.demo.service.LoginAuthService;
import com.michael.demo.service.LoginAuthServiceImpl;
import com.michael.demo.util.JwtToken;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    @Autowired
    LoginAuthService loginAuthService;

    @PostMapping("/login")
    public ResponseEntity<?> generateToken(@RequestBody TokenRequestResponse tokenRequestResponse) {
        return loginAuthService.generateToken(tokenRequestResponse);
    }


    @PostMapping("/validate-token")
    public ResponseEntity<Object> validateToken(@RequestBody TokenRequestResponse tokenReqRes){
        return ResponseEntity.ok(jwtToken.validateToken(tokenReqRes.getToken()));
    }

}

package com.michael.demo.service;

import com.michael.demo.model.TokenRequestResponse;
import org.springframework.http.ResponseEntity;

public interface LoginAuthService {
    public ResponseEntity<?> generateToken(TokenRequestResponse tokenRequestResponse);
}

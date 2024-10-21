package com.learning.demo.controllers;

import com.learning.demo.config.AuthenticationRequest;
import com.learning.demo.config.AuthenticationResponse;
import com.learning.demo.services.AuthenticationService;
import com.learning.demo.config.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request){
        return authenticationService.register(request);
    }

    @PostMapping("/register-admin")
    public ResponseEntity<AuthenticationResponse> registerAdmin(@RequestBody RegisterRequest request){
        return authenticationService.registerAdmin(request);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody AuthenticationRequest request){
        return authenticationService.login(request);
    }
}

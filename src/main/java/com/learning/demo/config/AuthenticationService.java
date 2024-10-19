package com.learning.demo.config;

import com.learning.demo.entities.Role;
import com.learning.demo.entities.User;
import com.learning.demo.repositories.UserRepository;
import com.learning.demo.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    public ResponseEntity<AuthenticationResponse> register(RegisterRequest request) {
        var user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
//                .role(Role.ADMIN)
                .build();
        userRepository.save(user);
        var jwt = jwtUtil.generateToken(user);
        return ResponseEntity.ok(AuthenticationResponse.builder().jwt(jwt).build());
    }

    public ResponseEntity<AuthenticationResponse> login(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        var user  = userRepository.findByUsername(request.getUsername())
                .orElseThrow();
        var jwt = jwtUtil.generateToken(user);
        return ResponseEntity.ok(AuthenticationResponse.builder().jwt(jwt).build());
    }
}

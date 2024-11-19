package org.example.postproject.service;

import lombok.RequiredArgsConstructor;
import org.example.postproject.config.JWTProvider;
import org.example.postproject.entities.User;
import org.example.postproject.payload.AuthenticationRequest;
import org.example.postproject.payload.AuthenticationResponse;
import org.example.postproject.repository.UserRepository;
import org.example.postproject.response.ResponseData;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JWTProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;

    public ResponseData<AuthenticationResponse> authenticate(AuthenticationRequest request) {
        authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()));
        Optional<User> byUsername = userRepository.findByUsername(request.getUsername());
        User user = byUsername.get();
        String token = jwtProvider.generateAccessToken(user);
        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        authenticationResponse.setToken(token);
        authenticationResponse.setUser(user);
        return ResponseData.successResponse(authenticationResponse);
    }

    public ResponseData<?> register(AuthenticationRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);
        return ResponseData.successResponse("User registered successfully");
    }
}
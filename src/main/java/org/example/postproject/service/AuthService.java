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


    /**
     *
     * bu method da username boyicha DB dan qidirib keladi
     * Agar DB da bunaqa username li user bo'lmasa 'Username yoki password xato' qaytaradi
     * user mavjud bolsa passwordlarni solishtiradi (encodlangan holatda) agar tog'ri kelmasa 'Username yoki Password xato'
     * qaytadi
     * agar hammasi to'g'ri kelsa return da  AuthenticationResponse holida jwt token va o'sha user qaytadi
     */
    public ResponseData<?> authenticate(AuthenticationRequest request) {
        Optional<User> byUsername = userRepository.findByUsername(request.getUsername());
        if (byUsername.isEmpty()) {
            return new ResponseData<>("Username yoki Password xato",false);
        }
        User user = byUsername.get();
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return new ResponseData<>("Username yoki Password xato",false);
        }
        String token = jwtProvider.generateAccessToken(user);
        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        authenticationResponse.setToken(token);
        authenticationResponse.setUser(user);
        return ResponseData.successResponse(authenticationResponse);
    }


    /**
     *
     * username va password keladi va shunga mos User yaratadi (password encodlangan holatda)
     */
    public ResponseData<?> register(AuthenticationRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);
        return ResponseData.successResponse("User registered successfully");
    }
}
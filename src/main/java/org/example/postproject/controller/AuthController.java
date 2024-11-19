package org.example.postproject.controller;

import lombok.RequiredArgsConstructor;
import org.example.postproject.payload.AuthenticationRequest;
import org.example.postproject.payload.AuthenticationResponse;
import org.example.postproject.response.ResponseData;
import org.example.postproject.service.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("sign-in")
    private ResponseData<AuthenticationResponse> signIn(@RequestBody AuthenticationRequest request) {
        return authService.authenticate(request);
    }
}


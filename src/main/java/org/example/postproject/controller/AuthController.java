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

    /**
     * @param request -- request ichida username va password keladi
     *                va shu boyicha authService ning ichidagi auth qiladigan joyiga uzatadi
     * @return
     */
    @PostMapping("sign-in")
    private ResponseData<?> signIn(@RequestBody AuthenticationRequest request) {
        return authService.authenticate(request);
    }

    /**
     * @param request - request ning ichida 2 ta string value keladi ular Username va password ()
     *                va shu boyicha authService ning ichidagi registratsiya qiladigan joyiga uzatadi
     */
    @PostMapping("registration")
    private ResponseData<?> registration(@RequestBody AuthenticationRequest request) {
        return this.authService.register(request);
    }
}


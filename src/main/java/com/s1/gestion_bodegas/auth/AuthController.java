package com.s1.gestion_bodegas.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")

public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public LoginResponse  login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }
}

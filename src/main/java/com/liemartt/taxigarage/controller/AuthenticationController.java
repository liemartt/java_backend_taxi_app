package com.liemartt.taxigarage.controller;

import com.liemartt.taxigarage.dto.SignInRequest;
import com.liemartt.taxigarage.dto.SignUpRequest;
import com.liemartt.taxigarage.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignUpRequest request) {
        return authenticationService.signup(request);
    }

    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestBody SignInRequest request) {
        return authenticationService.signin(request);
    }
}
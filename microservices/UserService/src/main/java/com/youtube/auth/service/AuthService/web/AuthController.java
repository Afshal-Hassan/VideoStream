package com.youtube.auth.service.AuthService.web;

import com.youtube.auth.service.AuthService.payloads.AuthResponse;
import com.youtube.auth.service.AuthService.payloads.GenericResponse;
import com.youtube.auth.service.AuthService.payloads.UserData;
import com.youtube.auth.service.AuthService.services.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService service;


    @PostMapping("/signup")
    public GenericResponse signUp(@Valid @RequestBody UserData userData) {
        service.signUp(userData);
        return new GenericResponse("User has been signed up successfully", null);
    }


    @PostMapping("/login")
    public GenericResponse login(@RequestBody UserData userData) {
        AuthResponse authResponse = service.login(userData);
        return new GenericResponse("User has been signed in successfully", authResponse);
    }
}

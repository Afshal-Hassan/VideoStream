package com.youtube.auth.service.AuthService.services;

import com.youtube.auth.service.AuthService.payloads.AuthResponse;
import com.youtube.auth.service.AuthService.payloads.UserData;
import com.youtube.auth.service.AuthService.utils.JwtProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceClient implements AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtProcessor jwtProcessor;

    @Override
    public void signUp(UserData userData) {
        userData.setPassword(passwordEncoder.encode(userData.getPassword()));
        userService.saveUser(userData);
    }

    @Override
    public AuthResponse login(UserData userData) {
        try {
            authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(userData.getEmail(), userData.getPassword()));
        } catch (BadCredentialsException ex) {
            throw new BadCredentialsException("Bad Credentials");
        }

        UserDetails userDetails = userService.loadUserByUsername(userData.getEmail());
        String token = jwtProcessor.generateToken(userDetails);
        return new AuthResponse(token);
    }
}

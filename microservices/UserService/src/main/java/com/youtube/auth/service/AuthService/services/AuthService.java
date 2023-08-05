package com.youtube.auth.service.AuthService.services;


import com.youtube.auth.service.AuthService.payloads.AuthResponse;
import com.youtube.auth.service.AuthService.payloads.UserData;



public interface AuthService {

    void signUp(UserData userData);

    AuthResponse login(UserData userData);



}

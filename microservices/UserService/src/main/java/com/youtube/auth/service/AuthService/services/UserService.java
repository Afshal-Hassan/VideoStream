package com.youtube.auth.service.AuthService.services;

import com.youtube.auth.service.AuthService.payloads.UserData;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService {

    void saveUser(UserData userData);

    UserDetails loadUserByUsername(String email) throws UsernameNotFoundException;

}

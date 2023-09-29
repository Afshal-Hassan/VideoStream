package com.youtube.VideoService.services;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


public interface UserService extends UserDetailsService {


    UserDetails loadUserByUsername(String email) throws UsernameNotFoundException;
}

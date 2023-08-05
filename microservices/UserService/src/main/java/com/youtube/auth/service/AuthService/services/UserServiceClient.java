package com.youtube.auth.service.AuthService.services;

import com.youtube.auth.service.AuthService.entities.CustomUserDetails;
import com.youtube.auth.service.AuthService.entities.User;
import com.youtube.auth.service.AuthService.payloads.UserData;
import com.youtube.auth.service.AuthService.repos.UserRepo;
import com.youtube.auth.service.AuthService.mappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceClient implements UserService, UserDetailsService {

    @Autowired
    private UserRepo repo;


    @Override
    public void saveUser(UserData userData) {
        repo.save(UserMapper.mapToEntity(userData));
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = repo.findByEmail(email);
        if (user == null) {

            throw new UsernameNotFoundException("User not found");
        }

        return new CustomUserDetails(UserMapper.mapToData(user));
    }
}

package com.youtube.VideoService.services;


import com.youtube.VideoService.config.UserPrincipal;
import com.youtube.VideoService.mappers.UserMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserServiceClient implements UserService {


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return new UserPrincipal(UserMapper.mapToData(email));
    }
}

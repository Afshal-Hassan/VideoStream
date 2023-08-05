package com.youtube.auth.service.AuthService.mappers;

import com.youtube.auth.service.AuthService.entities.User;
import com.youtube.auth.service.AuthService.payloads.UserData;
import org.springframework.stereotype.Component;


@Component
public final class UserMapper {


    public static User mapToEntity(UserData userData) {
        User user = new User();
        user.setName(userData.getName());
        user.setEmail(userData.getEmail());
        user.setPassword(userData.getPassword());
        user.setCountry(userData.getCountry());
        user.setPhoneNo(userData.getPhoneNo());
        return user;
    }


    public static UserData mapToData(User user) {
        UserData userData = new UserData();
        userData.setName(user.getName());
        userData.setEmail(user.getEmail());
        userData.setPassword(user.getPassword());
        userData.setCountry(user.getCountry());
        userData.setPhoneNo(user.getPhoneNo());
        return userData;
    }
}

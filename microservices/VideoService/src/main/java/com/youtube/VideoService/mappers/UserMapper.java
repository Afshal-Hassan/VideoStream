package com.youtube.VideoService.mappers;


import com.youtube.VideoService.payloads.rest_domains.inputs.UserData;
import org.springframework.stereotype.Component;


@Component
public final class UserMapper {


    private UserMapper() {

    }


    public static UserData mapToData(String email) {
        UserData userData = new UserData();
        userData.setEmail(email);
        userData.setPassword("");
        return userData;
    }
}

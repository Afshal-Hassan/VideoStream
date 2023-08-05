package com.youtube.auth.service.AuthService.payloads;


import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;



@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserData {


    @JsonProperty(value = "name")
    @NotBlank(message = "user name can't be empty or null")
    private String name;


    @JsonProperty(value = "email")
    @NotBlank(message = "user email can't be empty or null")
    @Size(min = 10, message = "user email must contain minimum of 10 characters")
    private String email;


    @JsonProperty(value = "password")
    @NotBlank(message = "password can't be empty or null")
    @Size(min = 8, message = "user email must contain minimum of 8 characters")
    private String password;


    @JsonProperty(value = "country")
    @NotBlank(message = "country can't be empty or null")
    private String country;


    @JsonProperty(value = "phoneNo")
    @NotBlank(message = "phoneNo can't be empty or null")
    private String phoneNo;

}

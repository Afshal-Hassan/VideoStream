package com.youtube.auth.service.AuthService.payloads;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class GenericResponse implements Serializable {


    @JsonProperty("message")
    private String message;

    @JsonProperty("data")
    private Object data;
}

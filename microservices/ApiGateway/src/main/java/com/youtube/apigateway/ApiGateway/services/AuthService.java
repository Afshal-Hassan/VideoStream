package com.youtube.apigateway.ApiGateway.services;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name = "AUTH-SERVICE")
public interface AuthService {

    @GetMapping("/validate")
    Object validateToken(@RequestParam("token") String token);
}

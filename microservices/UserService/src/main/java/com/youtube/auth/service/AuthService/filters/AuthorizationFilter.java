package com.youtube.auth.service.AuthService.filters;

import com.youtube.auth.service.AuthService.exceptions.UnauthorizedException;
import com.youtube.auth.service.AuthService.services.UserService;
import com.youtube.auth.service.AuthService.utils.JwtProcessor;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


import java.io.IOException;


@Component
public class AuthorizationFilter extends OncePerRequestFilter {


    @Autowired
    private JwtProcessor jwtProcessor;

    @Autowired
    private UserService userService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {


        String requestHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        String token = null;
        String email = null;


        if (requestHeader != null && requestHeader.startsWith("Bearer ")) {


            token = requestHeader.substring(7);


            try {
                email = jwtProcessor.extractUsername(token);
            } catch (IllegalArgumentException | ExpiredJwtException | MalformedJwtException ex) {
                throw new UnauthorizedException("Unable to authorized");
            }


            UserDetails userDetails = userService.loadUserByUsername(email);


            if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {


                if (jwtProcessor.validateToken(token, userDetails)) {


                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    SecurityContextHolder.getContext().setAuthentication(authentication);


                } else {
                    throw new UnauthorizedException("Token is not valid");
                }
            }
        }


        filterChain.doFilter(request, response);
    }
}

package com.proyectoV1.reservaSalones.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


import java.io.IOException;
import java.util.Collections;


public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
   @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response){
        AuthCredentials authCredentials  = new AuthCredentials();
        try {
            authCredentials = new ObjectMapper().readValue(request.getReader(), AuthCredentials.class);

        }catch (IOException e){

        }
        UsernamePasswordAuthenticationToken usernamePAT = new UsernamePasswordAuthenticationToken(
                authCredentials.getUsername(),
                authCredentials.getPassword(),
                Collections.emptyList()
        );
      return super.attemptAuthentication(request, response);

   }
   @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws  IOException, ServletException{
       UserDetailsImpl userDetails = (UserDetailsImpl) authResult.getPrincipal();
       String token = JwtUtil.createToken(userDetails.getNombre(), userDetails.getUsername());
       response.setHeader("Authorization", "Bearer " + token);
       response.getWriter().flush();
       super.successfulAuthentication(request, response, chain ,authResult);
   }
}
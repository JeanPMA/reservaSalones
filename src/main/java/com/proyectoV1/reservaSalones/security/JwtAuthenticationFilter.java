package com.proyectoV1.reservaSalones.security;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.proyectoV1.reservaSalones.domain.entities.Usuario;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private JwtUtil jwtUtil;
    public JwtAuthenticationFilter(JwtUtil jwtUtil){
        this.jwtUtil = jwtUtil;
    }
   @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response){

       Usuario usuario = null;
       String username = "";
       String password = "";
       try{
           usuario = new ObjectMapper().readValue(request.getInputStream(), Usuario.class);
           username = usuario.getUsuario();
           password = usuario.getPassword();
       } catch (StreamReadException e) {
           throw new RuntimeException(e);
       } catch (DatabindException e) {
           throw new RuntimeException(e);
       } catch (IOException e) {
           throw new RuntimeException(e);
       }

       UsernamePasswordAuthenticationToken authenticationToken =
               new UsernamePasswordAuthenticationToken(username, password);

       return getAuthenticationManager().authenticate(authenticationToken);

   }
   @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws  IOException, ServletException{
       UserDetailsImpl userDetails = (UserDetailsImpl) authResult.getPrincipal();

       String token = JwtUtil.createToken(userDetails.getNombre(), userDetails.getUsername());

       response.addHeader("Authorization", token);

       Map<String, Object> httpResponse = new HashMap<>();
       httpResponse.put("token", token);
       httpResponse.put("Message", "Autenticacion Correcta");
       httpResponse.put("Username", userDetails.getUsername());

       response.getWriter().write(new ObjectMapper().writeValueAsString(httpResponse));
       response.setStatus(HttpStatus.OK.value());
       response.setContentType(MediaType.APPLICATION_JSON_VALUE);
       response.getWriter().flush();

       super.successfulAuthentication(request, response, chain ,authResult);
   }
}

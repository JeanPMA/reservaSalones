package com.proyectoV1.reservaSalones.web.rest;

import com.proyectoV1.reservaSalones.domain.entities.Usuario;
import com.proyectoV1.reservaSalones.security.AuthCredentials;
import com.proyectoV1.reservaSalones.security.JwtAuthenticationFilter;
import com.proyectoV1.reservaSalones.security.JwtUtil;
import com.proyectoV1.reservaSalones.security.UserDetailServiceImpl;
import com.proyectoV1.reservaSalones.web.rest.response.JwtResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.authentication.AuthenticationManager;


@RestController
@RequestMapping("/v1")
public class LoginController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;



    @GetMapping("/hello")
    public String hello(){
        return "Hello World Not Secured";
    }

    @GetMapping("/helloSecured")
    public String helloSecured(){
        return "Hello World Secured";
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody AuthCredentials authCredentials) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authCredentials.getUsername(), authCredentials.getPassword()));

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        String jwt = jwtUtil.createToken(userDetails.getUsername(), userDetails.getAuthorities());

        return ResponseEntity.ok(new JwtResponse(jwt));
    }

    @GetMapping("/accessAdmin")
    @PreAuthorize("hasRole('ADMIN')")
    public String accessAdmin(){
        return "Hola, has accedito con rol de ADMIN";
    }

    @GetMapping("/accessUser")
    @PreAuthorize("hasRole('USER')")
    public String accessUser(){
        return "Hola, has accedito con rol de USER";
    }
    @GetMapping("/accessTodo")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public String todosroles(){
        return "Hola, has accedito con rol de TODOS";
    }

}

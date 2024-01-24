package com.proyectoV1.reservaSalones.web.rest;

import com.proyectoV1.reservaSalones.security.AuthCredentials;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
public class LoginController {
    @GetMapping("/hello")
    public String hello(){
        return "Hello World Not Secured";
    }

    @GetMapping("/helloSecured")
    public String helloSecured(){
        return "Hello World Secured";
    }
    /*@PostMapping("/login")
    public ResponseEntity<String> authenticate(@RequestBody AuthCredentials authRequest) {
        // Aquí deberías manejar la autenticación y generar el token JWT
        // Retorna el token en la respuesta si la autenticación es exitosa
        // Sino, puedes manejar los errores y retornar una respuesta adecuada
        return ResponseEntity.ok("Token JWT aquí");
    }*/
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

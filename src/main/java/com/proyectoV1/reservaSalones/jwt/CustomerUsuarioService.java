package com.proyectoV1.reservaSalones.jwt;

import com.proyectoV1.reservaSalones.domain.entities.Usuario;
import com.proyectoV1.reservaSalones.repositories.UsuarioRepository;
import com.proyectoV1.reservaSalones.services.UsuarioService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;

@Slf4j
@Service
public class CustomerUsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    private Usuario usuario;

    public Usuario cargarUsuarioByNombre(String username){
        usuario = usuarioRepository.findByNombreUsuario(username);
        if (!Objects.isNull(usuario)){
            //return  new org.springframework.security.core.userdetails.User(usuario.getUsuario(), usuario.getPassword(), new ArrayList<>());
            throw new RuntimeException();
        }
        else {
            throw new RuntimeException();
        }
    }
}

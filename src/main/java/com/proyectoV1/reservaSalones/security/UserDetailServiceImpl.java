package com.proyectoV1.reservaSalones.security;

import com.proyectoV1.reservaSalones.domain.entities.Usuario;
import com.proyectoV1.reservaSalones.repositories.UsuarioRepository;
import org.hibernate.sql.ast.tree.expression.Over;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Override
    public UserDetails loadUserByUsername(String usuario) throws UsernameNotFoundException{
        Usuario usuario1 = usuarioRepository
                .findOneByUsuario(usuario)
                .orElseThrow(() -> new UsernameNotFoundException("El usuario con nombre " + usuario + "no existe."));
        return new UserDetailsImpl(usuario1);
    }
}

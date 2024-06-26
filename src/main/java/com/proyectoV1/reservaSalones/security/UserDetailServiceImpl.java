package com.proyectoV1.reservaSalones.security;

import com.proyectoV1.reservaSalones.domain.entities.Usuario;
import com.proyectoV1.reservaSalones.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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
                .findOneByUsername(usuario)
                .orElseThrow(() -> new UsernameNotFoundException("El usuario con nombre " + usuario + "no existe."));

        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + usuario1.getRol().getNombre());


        return new UserDetailsImpl(usuario1);
    }
}

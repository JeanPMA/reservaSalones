package com.proyectoV1.reservaSalones.repositories;

import com.proyectoV1.reservaSalones.domain.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByNombreUsuario(String usuario);
}

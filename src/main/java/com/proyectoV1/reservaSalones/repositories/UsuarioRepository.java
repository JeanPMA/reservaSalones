package com.proyectoV1.reservaSalones.repositories;

import com.proyectoV1.reservaSalones.domain.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findOneByUsername(String username);

    @Query("SELECT u.estado FROM Usuario u WHERE u.username = :username")
    Integer getUserStatus(@Param("username") String username);
}

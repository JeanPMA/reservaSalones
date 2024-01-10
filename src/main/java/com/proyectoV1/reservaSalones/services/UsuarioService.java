package com.proyectoV1.reservaSalones.services;

import com.proyectoV1.reservaSalones.domain.entities.Usuario;
import com.proyectoV1.reservaSalones.dto.TelefonoSalonDTO;
import com.proyectoV1.reservaSalones.dto.UsuarioDTO;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    List<Usuario> listarUsuario();

    UsuarioDTO save(UsuarioDTO dto);
    Optional<UsuarioDTO> getUsuarioById(Long usuarioId);
    void delete(Long usuarioId);
}
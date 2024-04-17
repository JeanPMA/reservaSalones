package com.proyectoV1.reservaSalones.services;

import com.proyectoV1.reservaSalones.domain.entities.Usuario;
import com.proyectoV1.reservaSalones.dto.UsuarioDTO;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    List<UsuarioDTO> listarUsuario();

    UsuarioDTO save(UsuarioDTO dto);
    Optional<UsuarioDTO> getUsuarioById(Long usuarioId);
    void delete(Long usuarioId);
    Usuario parcial(UsuarioDTO dto, Long usuarioId);

    Optional<UsuarioDTO> getUsuarioByUsername(String username);
}

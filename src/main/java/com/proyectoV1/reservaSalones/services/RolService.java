package com.proyectoV1.reservaSalones.services;

import com.proyectoV1.reservaSalones.dto.RolDTO;

import java.util.List;
import java.util.Optional;

public interface RolService {
    List<RolDTO> listarRol();

    RolDTO save(RolDTO dto);
    Optional<RolDTO> getRolById(Integer rolId);
    void delete(Integer rolId);
}

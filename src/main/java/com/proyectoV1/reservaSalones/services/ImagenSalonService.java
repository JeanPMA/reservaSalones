package com.proyectoV1.reservaSalones.services;

import com.proyectoV1.reservaSalones.domain.entities.ImagenSalon;
import com.proyectoV1.reservaSalones.domain.entities.Usuario;
import com.proyectoV1.reservaSalones.dto.ImagenSalonDTO;
import com.proyectoV1.reservaSalones.dto.UsuarioDTO;

import java.util.List;
import java.util.Optional;

public interface ImagenSalonService {
    List<ImagenSalonDTO> listarImagenes();

    ImagenSalonDTO save(ImagenSalonDTO dto);
    Optional<ImagenSalonDTO> getImagenById(Long imagenSalonId);
    void delete(Long imagenSalonId);
}

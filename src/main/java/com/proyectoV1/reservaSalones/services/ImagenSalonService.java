package com.proyectoV1.reservaSalones.services;


import com.proyectoV1.reservaSalones.dto.ImagenSalonDTO;


import java.util.List;
import java.util.Optional;

public interface ImagenSalonService {
    List<ImagenSalonDTO> listarImagenes(Integer salonId);

    ImagenSalonDTO save(ImagenSalonDTO dto);
    Optional<ImagenSalonDTO> getImagenById(Long imagenSalonId);
    void delete(Long imagenSalonId);
}

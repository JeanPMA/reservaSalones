package com.proyectoV1.reservaSalones.services.mapper;

import com.proyectoV1.reservaSalones.domain.entities.ImagenSalon;
import com.proyectoV1.reservaSalones.dto.ImagenSalonDTO;
import org.springframework.stereotype.Component;

@Component
public class ImagenSalonMapper implements CustomMapper<ImagenSalonDTO, ImagenSalon>{
    @Override
    public ImagenSalonDTO toDto(ImagenSalon imagenSalon) {
        ImagenSalonDTO imagenSalonDTO = new ImagenSalonDTO();
        imagenSalonDTO.setId(imagenSalon.getId());
        imagenSalonDTO.setNombre(imagenSalon.getNombre());
        imagenSalonDTO.setImagen_id(imagenSalon.getImagen_id());
        imagenSalonDTO.setImagen_url(imagenSalon.getImagen_url());
        imagenSalonDTO.setSalon(imagenSalon.getSalon());


        return imagenSalonDTO;
    }
    @Override
    public ImagenSalon toEntity(ImagenSalonDTO imagenSalonDTO) {
        ImagenSalon imagenSalon = new ImagenSalon();
        imagenSalon.setId(imagenSalonDTO.getId());
        imagenSalon.setNombre(imagenSalonDTO.getNombre());
        imagenSalon.setImagen_id(imagenSalonDTO.getImagen_id());
        imagenSalon.setImagen_url(imagenSalonDTO.getImagen_url());
        imagenSalon.setSalon(imagenSalonDTO.getSalon());

        return imagenSalon;
    }
}

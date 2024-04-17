package com.proyectoV1.reservaSalones.services.mapper;

import com.proyectoV1.reservaSalones.domain.entities.Servicio;
import com.proyectoV1.reservaSalones.dto.ServicioDTO;
import org.springframework.stereotype.Component;

@Component
public class ServicioMapper implements CustomMapper<ServicioDTO, Servicio>{
    @Override
    public ServicioDTO toDto(Servicio servicio) {
        ServicioDTO servicioDTO = new ServicioDTO();
        servicioDTO.setId(servicio.getId());
        servicioDTO.setNombre(servicio.getNombre());
        servicioDTO.setDetalle(servicio.getDetalle());
        servicioDTO.setEstado(servicio.getEstado());
        servicioDTO.setCreated_at(servicio.getCreated_at());

        return servicioDTO;
    }
    @Override
    public Servicio toEntity(ServicioDTO servicioDTO) {
        Servicio servicio = new Servicio();
        servicio.setId(servicioDTO.getId());
        servicio.setNombre(servicioDTO.getNombre());
        servicio.setDetalle(servicioDTO.getDetalle());
        servicio.setEstado(servicioDTO.getEstado());
        servicio.setCreated_at(servicioDTO.getCreated_at());

        return servicio;
    }
}

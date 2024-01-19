package com.proyectoV1.reservaSalones.services.mapper;

import com.proyectoV1.reservaSalones.domain.entities.Salon;
import com.proyectoV1.reservaSalones.dto.SalonDTO;
import org.springframework.stereotype.Component;

@Component

public class SalonMapper implements CustomMapper<SalonDTO, Salon>{
    @Override
    public SalonDTO toDto(Salon salon) {
        SalonDTO salonDTO = new SalonDTO();
        salonDTO.setId(salon.getId());
        salonDTO.setNombre(salon.getNombre());
        salonDTO.setDireccion(salon.getDireccion());
        salonDTO.setCapacidad(salon.getCapacidad());
        salonDTO.setDescripcion(salon.getDescripcion());
        salonDTO.setBanner_id(salon.getBanner_id());
        salonDTO.setBanner_url(salon.getBanner_url());
        salonDTO.setTarifa(salon.getTarifa());
        salonDTO.setEstado(salon.getEstado());
        salonDTO.setUsuario(salon.getUsuario());
        salonDTO.setCreated_at(salon.getCreated_at());
        salonDTO.setServicios(salon.getServicios());

        return salonDTO;
    }
    @Override
    public Salon toEntity(SalonDTO salonDTO) {
        Salon salon = new Salon();
        salon.setId(salonDTO.getId());
        salon.setNombre(salonDTO.getNombre());
        salon.setDireccion(salonDTO.getDireccion());
        salon.setCapacidad(salonDTO.getCapacidad());
        salon.setDescripcion(salonDTO.getDescripcion());
        salon.setBanner_id(salonDTO.getBanner_id());
        salon.setBanner_url(salonDTO.getBanner_url());
        salon.setTarifa(salonDTO.getTarifa());
        salon.setEstado(salonDTO.getEstado());
        salon.setUsuario(salonDTO.getUsuario());
        salon.setCreated_at(salonDTO.getCreated_at());
        salon.setServicios(salonDTO.getServicios());

        return salon;
    }
}

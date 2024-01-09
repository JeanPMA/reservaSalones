package com.proyectoV1.reservaSalones.services.mapper;

import com.proyectoV1.reservaSalones.domain.entities.Rol;
import com.proyectoV1.reservaSalones.domain.entities.TelefonoSalon;
import com.proyectoV1.reservaSalones.dto.RolDTO;
import com.proyectoV1.reservaSalones.dto.TelefonoSalonDTO;

public class TelefonoSalonMapper implements CustomMapper<TelefonoSalonDTO, TelefonoSalon>{
    @Override
    public TelefonoSalonDTO toDto(TelefonoSalon telefonoSalon) {
        TelefonoSalonDTO telefonoSalonDTO = new TelefonoSalonDTO();
        telefonoSalonDTO.setId(telefonoSalon.getId());
        telefonoSalonDTO.setNumero(telefonoSalon.getNumero());
        telefonoSalonDTO.setSalon(telefonoSalon.getSalon());

        return telefonoSalonDTO;
    }
    @Override
    public TelefonoSalon toEntity(TelefonoSalonDTO telefonoSalonDTO) {
        TelefonoSalon telefonoSalon = new TelefonoSalon();
        telefonoSalon.setId(telefonoSalonDTO.getId());
        telefonoSalon.setNumero(telefonoSalonDTO.getNumero());
        telefonoSalon.setSalon(telefonoSalonDTO.getSalon());

        return telefonoSalon;
    }
}

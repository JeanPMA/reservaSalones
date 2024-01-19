package com.proyectoV1.reservaSalones.services;

import com.proyectoV1.reservaSalones.domain.entities.Salon;
import com.proyectoV1.reservaSalones.domain.entities.Servicio;
import com.proyectoV1.reservaSalones.domain.entities.Usuario;
import com.proyectoV1.reservaSalones.dto.SalonDTO;
import com.proyectoV1.reservaSalones.dto.UsuarioDTO;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface SalonService {
    List<SalonDTO> listarSalon();

    SalonDTO save(SalonDTO dto);
    Optional<SalonDTO> getSalonById(Integer salonId);
    void delete(Integer salonId);
    Salon parcial(Salon dto, Integer salonId);
}

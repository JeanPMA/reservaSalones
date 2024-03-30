package com.proyectoV1.reservaSalones.services;

import com.proyectoV1.reservaSalones.domain.entities.Salon;
import com.proyectoV1.reservaSalones.dto.SalonAvgDTO;
import com.proyectoV1.reservaSalones.dto.SalonDTO;

import java.util.List;
import java.util.Optional;

public interface SalonService {
    List<SalonDTO> listarSalon();
    List<SalonDTO> listarSalonPorCalificacion();
    List<SalonDTO> listarSalonParaUsuarios();
    List<SalonAvgDTO> listarSalonPorCalificacionAuth();
    List<SalonDTO> listarSalonParaUsuariosAuth();
    SalonDTO save(SalonDTO dto, String username);
    SalonDTO saveForAdmin(SalonDTO dto);
    Optional<SalonDTO> getSalonById(Integer salonId);
    List<SalonDTO> getSalonByUsername(String username);
    void delete(Integer salonId);
    Salon parcial(Salon dto, Integer salonId);
}

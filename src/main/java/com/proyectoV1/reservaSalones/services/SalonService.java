package com.proyectoV1.reservaSalones.services;

import com.proyectoV1.reservaSalones.domain.entities.Salon;
import com.proyectoV1.reservaSalones.domain.entities.Servicio;
import com.proyectoV1.reservaSalones.domain.entities.SolicitudReserva;
import com.proyectoV1.reservaSalones.domain.entities.Usuario;
import com.proyectoV1.reservaSalones.dto.SalonDTO;
import com.proyectoV1.reservaSalones.dto.UsuarioDTO;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface SalonService {
    List<SalonDTO> listarSalon();
    List<SalonDTO> listarSalonPorCalificacion();
    List<SalonDTO> listarSalonParaUsuarios();
    List<SalonDTO> listarSalonPorCalificacionAuth();
    List<SalonDTO> listarSalonParaUsuariosAuth();
    SalonDTO save(SalonDTO dto, String username);
    SalonDTO saveForAdmin(SalonDTO dto);
    Optional<SalonDTO> getSalonById(Integer salonId);
    List<SalonDTO> getSalonByUsername(String username);
    void delete(Integer salonId);
    Salon parcial(Salon dto, Integer salonId);
}

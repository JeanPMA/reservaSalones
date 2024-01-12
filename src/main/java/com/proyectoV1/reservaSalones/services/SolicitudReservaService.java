package com.proyectoV1.reservaSalones.services;

import com.proyectoV1.reservaSalones.dto.SolicitudReservaDTO;
import com.proyectoV1.reservaSalones.dto.UsuarioDTO;

import java.util.List;
import java.util.Optional;

public interface SolicitudReservaService {
    List<SolicitudReservaDTO> listarSolicitudReserva();

    SolicitudReservaDTO save(SolicitudReservaDTO dto);
    Optional<SolicitudReservaDTO> getSolicitudById(Long solicitudId);
    void delete(Long solicitudId);
}

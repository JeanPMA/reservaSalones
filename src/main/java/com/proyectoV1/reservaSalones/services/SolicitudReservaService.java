package com.proyectoV1.reservaSalones.services;

import com.proyectoV1.reservaSalones.domain.entities.SolicitudReserva;
import com.proyectoV1.reservaSalones.dto.SolicitudReservaDTO;

import java.util.List;
import java.util.Optional;

public interface SolicitudReservaService {
    List<SolicitudReservaDTO> listarSolicitudReserva();

    SolicitudReservaDTO save(SolicitudReservaDTO dto, String username);
    Optional<SolicitudReservaDTO> getSolicitudById(Long solicitudId);
    void delete(Long solicitudId);

    List<SolicitudReserva> findByTipoSRNotNombre(String username);
    SolicitudReserva calificacionReserva(SolicitudReserva dto, Long solicitudId);

    List<SolicitudReservaDTO> obtenerSolicitudesPorSalonYUsuario(String username);
    List<SolicitudReservaDTO> obtenerReservasPorSalonYUsuario(String username);

    SolicitudReservaDTO cambiarTipoSR(SolicitudReservaDTO dto, Long solicitudId);
}

package com.proyectoV1.reservaSalones.services;

import com.proyectoV1.reservaSalones.domain.entities.Salon;
import com.proyectoV1.reservaSalones.domain.entities.TipoSolicitudReserva;
import com.proyectoV1.reservaSalones.dto.TipoSolicitudReservaDTO;

import java.util.List;
import java.util.Optional;

public interface TipoSolicitudReservaService {
    List<TipoSolicitudReservaDTO> listarTipoSR();

    TipoSolicitudReservaDTO save(TipoSolicitudReservaDTO dto);
    Optional<TipoSolicitudReservaDTO> getTipoSRById(Integer tipoSRId);
    void delete(Integer tipoSRId);
    TipoSolicitudReserva parcial(TipoSolicitudReserva dto, Integer salonId);
}

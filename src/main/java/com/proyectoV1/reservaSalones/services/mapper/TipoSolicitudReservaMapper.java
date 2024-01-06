package com.proyectoV1.reservaSalones.services.mapper;

import com.proyectoV1.reservaSalones.domain.entities.TipoSolicitudReserva;
import com.proyectoV1.reservaSalones.dto.TipoSolicitudReservaDTO;
import org.springframework.stereotype.Component;

@Component
public class TipoSolicitudReservaMapper implements CustomMapper<TipoSolicitudReservaDTO, TipoSolicitudReserva>{
    @Override
    public TipoSolicitudReservaDTO toDto(TipoSolicitudReserva tipoSolicitudReserva) {
        TipoSolicitudReservaDTO tipoSolicitudReservaDTO = new TipoSolicitudReservaDTO();
        tipoSolicitudReservaDTO.setId(tipoSolicitudReserva.getId());
        tipoSolicitudReservaDTO.setNombre(tipoSolicitudReserva.getNombre());
        tipoSolicitudReservaDTO.setCreated_at(tipoSolicitudReserva.getCreated_at());

        return tipoSolicitudReservaDTO;
    }

    @Override
    public TipoSolicitudReserva toEntity(TipoSolicitudReservaDTO tipoSolicitudReservaDTO) {
        TipoSolicitudReserva tipoSolicitudReserva = new TipoSolicitudReserva();
        tipoSolicitudReserva.setId(tipoSolicitudReservaDTO.getId());
        tipoSolicitudReserva.setNombre(tipoSolicitudReservaDTO.getNombre());
        tipoSolicitudReserva.setCreated_at(tipoSolicitudReservaDTO.getCreated_at());

        return tipoSolicitudReserva;
    }
}

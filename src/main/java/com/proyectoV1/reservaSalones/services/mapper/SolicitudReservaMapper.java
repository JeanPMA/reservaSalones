package com.proyectoV1.reservaSalones.services.mapper;

import com.proyectoV1.reservaSalones.domain.entities.SolicitudReserva;
import com.proyectoV1.reservaSalones.dto.SolicitudReservaDTO;
import org.springframework.stereotype.Component;

@Component
public class SolicitudReservaMapper implements CustomMapper<SolicitudReservaDTO, SolicitudReserva>{
    @Override
    public SolicitudReservaDTO toDto(SolicitudReserva solicitudReserva) {
        SolicitudReservaDTO solicitudReservaDTO = new SolicitudReservaDTO();
        solicitudReservaDTO.setId(solicitudReserva.getId());
        solicitudReservaDTO.setDetalle(solicitudReserva.getDetalle());
        solicitudReservaDTO.setFecha_reserva(solicitudReserva.getFecha_reserva());
        solicitudReservaDTO.setFecha_emision(solicitudReserva.getFecha_emision());
        solicitudReservaDTO.setMotivo(solicitudReserva.getMotivo());
        solicitudReservaDTO.setSalon(solicitudReserva.getSalon());
        solicitudReservaDTO.setUsuario(solicitudReserva.getUsuario());
        solicitudReservaDTO.setTipoSR(solicitudReserva.getTipoSR());

        return solicitudReservaDTO;
    }
    @Override
    public SolicitudReserva toEntity(SolicitudReservaDTO solicitudReservaDTO) {
        SolicitudReserva solicitudReserva = new SolicitudReserva();
        solicitudReserva.setId(solicitudReservaDTO.getId());
        solicitudReserva.setDetalle(solicitudReservaDTO.getDetalle());
        solicitudReserva.setFecha_reserva(solicitudReservaDTO.getFecha_reserva());
        solicitudReserva.setFecha_emision(solicitudReservaDTO.getFecha_emision());
        solicitudReserva.setMotivo(solicitudReservaDTO.getMotivo());
        solicitudReserva.setSalon(solicitudReservaDTO.getSalon());
        solicitudReserva.setUsuario(solicitudReservaDTO.getUsuario());
        solicitudReserva.setTipoSR(solicitudReservaDTO.getTipoSR());

        return solicitudReserva;
    }
}

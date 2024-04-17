package com.proyectoV1.reservaSalones.repositories;

import com.proyectoV1.reservaSalones.domain.entities.SolicitudReserva;
import com.proyectoV1.reservaSalones.dto.SolicitudReservaDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SolicitudReservaRepository extends JpaRepository<SolicitudReserva, Long> {
    @Query("SELECT s FROM SolicitudReserva s WHERE s.tipoSR.nombre <> :tipoSRNombre AND s.usuario.username = :username ORDER BY s.fecha_emision DESC")
    List<SolicitudReserva> findByTipoSRNombreNotAndUsuarioUsernameOrderByFecha_emisionDesc(String tipoSRNombre, String username);
    @Query("SELECT new com.proyectoV1.reservaSalones.dto.SolicitudReservaDTO(s.id, s.detalle, s.fecha_reserva, s.fecha_emision, s.servicio, s.motivo, s.puntuacion, s.salon, s.usuario, s.tipoSR) FROM SolicitudReserva s JOIN s.salon sal JOIN sal.usuario u WHERE u.username = :username AND s.tipoSR.nombre IN ('PENDIENTE', 'RECHAZADO') ORDER BY s.fecha_emision")
    List<SolicitudReservaDTO> findSolicitudesBySalonUsuarioUsername(@Param("username") String username);
    @Query("SELECT new com.proyectoV1.reservaSalones.dto.SolicitudReservaDTO(s.id, s.detalle, s.fecha_reserva, s.fecha_emision, s.servicio, s.motivo, s.puntuacion, s.salon, s.usuario, s.tipoSR) FROM SolicitudReserva s JOIN s.salon sal JOIN sal.usuario u WHERE u.username = :username AND s.tipoSR.nombre IN ('ACEPTADO', 'CANCELADO') ORDER BY s.fecha_emision")
    List<SolicitudReservaDTO> findReservasBySalonUsuarioUsername(@Param("username") String username);
}

package com.proyectoV1.reservaSalones.repositories;

import com.proyectoV1.reservaSalones.domain.entities.SolicitudReserva;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SolicitudReservaRepository extends JpaRepository<SolicitudReserva, Long> {
    List<SolicitudReserva> findByTipoSRNombreNot(String nombre);
}

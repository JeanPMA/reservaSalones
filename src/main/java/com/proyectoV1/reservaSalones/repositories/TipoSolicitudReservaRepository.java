package com.proyectoV1.reservaSalones.repositories;

import com.proyectoV1.reservaSalones.domain.entities.TipoSolicitudReserva;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TipoSolicitudReservaRepository extends JpaRepository<TipoSolicitudReserva, Integer> {
    Optional<TipoSolicitudReserva> findByNombre(String nombre);
}

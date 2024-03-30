package com.proyectoV1.reservaSalones.repositories;

import com.proyectoV1.reservaSalones.domain.entities.Servicio;
import com.proyectoV1.reservaSalones.dto.ServicioDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ServicioRepository extends JpaRepository<Servicio, Integer> {
    @Query("SELECT NEW com.proyectoV1.reservaSalones.dto.ServicioDTO(" +
            "s.id, s.nombre, s.detalle, s.estado, s.created_at) " +
            "FROM Servicio s " +
            "WHERE s.estado = 1 " +
            "ORDER BY s.nombre")
    List<ServicioDTO> findAllServicioActivo();

    List<Servicio> findAllByOrderByNombre();
}

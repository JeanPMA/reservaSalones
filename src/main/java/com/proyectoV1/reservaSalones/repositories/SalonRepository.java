package com.proyectoV1.reservaSalones.repositories;

import com.proyectoV1.reservaSalones.domain.entities.Salon;
import com.proyectoV1.reservaSalones.dto.SalonDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SalonRepository extends JpaRepository<Salon, Integer> {

     List<Salon> findAllByOrderByNombre();
     List<Salon> findAllByUsuarioUsernameOrderByNombre(String username);
     @Query("SELECT NEW com.proyectoV1.reservaSalones.dto.SalonDTO(" +
             "s.nombre, s.direccion, s.capacidad, s.descripcion, s.banner_id, s.banner_url, s.estado) " +
             "FROM Salon s " +
             "WHERE s.estado = 1 " +
             "ORDER BY s.nombre")
     List<SalonDTO> findAllByEstadoOrderByNombre();

     @Query("SELECT NEW com.proyectoV1.reservaSalones.dto.SalonDTO(" +
             "s.nombre, s.direccion, s.capacidad, s.descripcion, s.banner_id, s.banner_url, s.estado) " +
             "FROM Salon s " +
             "LEFT JOIN SolicitudReserva sol  ON sol.salon = s " +
             "WHERE s.estado = 1 " +
             "GROUP BY s.id, s.nombre,s.direccion, s.capacidad, s.descripcion, s.banner_id, s.banner_url, s.estado " +
             "ORDER BY COALESCE(AVG(sol.puntuacion), 0) DESC")
     List<SalonDTO> listaSalonesByCalificacion();

     @Query("SELECT s FROM Salon s LEFT JOIN FETCH s.servicios WHERE s.estado = 1 ORDER BY s.created_at DESC")
     List<Salon> findAllSalonForUserAuth();

     @Query("SELECT DISTINCT s, COALESCE((SELECT AVG(DISTINCT sol.puntuacion) FROM SolicitudReserva sol WHERE sol.salon = s), 0) as avgPuntuacion " +
             "FROM Salon s " +
             "WHERE s.estado = 1 " +
             "ORDER BY avgPuntuacion DESC")
     List<Object[]> listaSalonesByCalificacionForUserAuth();
    /* @Query("SELECT NEW com.proyectoV1.reservaSalones.dto.SalonDTO(" +
             "s.id, s.nombre, s.direccion, s.capacidad, s.descripcion, s.banner_id, s.banner_url, s.tarifa, s.estado, s.usuario, s.created_at) " +
             "FROM Salon s " +
             "LEFT JOIN SolicitudReserva sol  ON sol.salon = s " +
             "WHERE s.estado = 1 " +
             "GROUP BY s.id, s.nombre, s.direccion, s.capacidad, s.descripcion, s.banner_id, s.banner_url, s.tarifa, s.estado, s.usuario, s.created_at " +
             "ORDER BY COALESCE(AVG(sol.puntuacion), 0) DESC")
     List<SalonDTO> listaSalonesByCalificacionForUserAuth();*/
}

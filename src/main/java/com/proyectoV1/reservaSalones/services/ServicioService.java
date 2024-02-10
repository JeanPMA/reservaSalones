package com.proyectoV1.reservaSalones.services;

import com.proyectoV1.reservaSalones.domain.entities.Servicio;
import com.proyectoV1.reservaSalones.dto.ServicioDTO;

import java.util.List;
import java.util.Optional;

public interface ServicioService {
    List<ServicioDTO> listarServicio();

    ServicioDTO save(ServicioDTO dto);
    Optional<ServicioDTO> getServicioById(Integer servicioId);
    void delete(Integer servicioId);
    Servicio parcial(ServicioDTO dto, Integer servicioId);
    ServicioDTO findById(Integer servicioId);
}

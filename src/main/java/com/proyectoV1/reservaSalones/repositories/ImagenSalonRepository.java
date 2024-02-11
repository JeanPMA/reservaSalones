package com.proyectoV1.reservaSalones.repositories;

import com.proyectoV1.reservaSalones.domain.entities.ImagenSalon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImagenSalonRepository  extends JpaRepository<ImagenSalon, Long> {
    List<ImagenSalon> findBySalonId(Integer salonId);
}

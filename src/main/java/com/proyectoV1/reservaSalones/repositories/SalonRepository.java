package com.proyectoV1.reservaSalones.repositories;

import com.proyectoV1.reservaSalones.domain.entities.Salon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalonRepository extends JpaRepository<Salon, Integer> {
}

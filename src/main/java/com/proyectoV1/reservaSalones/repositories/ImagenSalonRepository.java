package com.proyectoV1.reservaSalones.repositories;

import com.proyectoV1.reservaSalones.domain.entities.ImagenSalon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ImagenSalonRepository  extends JpaRepository<ImagenSalon, Long> {
    List<ImagenSalon> findBySalonId(Integer salonId);
    @Query("SELECT COUNT(im) FROM ImagenSalon im WHERE im.salon.id = :salonId")
    Long countBySalonId(@Param("salonId") Integer salonId);
}

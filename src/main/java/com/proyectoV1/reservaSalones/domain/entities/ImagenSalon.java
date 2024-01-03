package com.proyectoV1.reservaSalones.domain.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "imagen_salon")
public class ImagenSalon {
    @Id
    @SequenceGenerator(name = "imagen_salon_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "imagen_salon_sequence")
    @Column(nullable = false)
    private Long id;

    @Column(name = "url", length = 255, nullable = false)
    private String url;
}

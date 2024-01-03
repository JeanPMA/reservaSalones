package com.proyectoV1.reservaSalones.domain.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "servicio")
public class Servicio {
    @Id
    @SequenceGenerator(name = "servicio_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "servicio_sequence")
    @Column(nullable = false)
    private Integer id;

    @Column(name = "nombre", length = 100, nullable = false)
    private String nombre;

    @Column(name = "detalle", length = 255, nullable = false)
    private String detalle;

    @Column(name = "estado", nullable = false)
    private Integer estado;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP", nullable = false)
    private LocalDateTime created_at;
}

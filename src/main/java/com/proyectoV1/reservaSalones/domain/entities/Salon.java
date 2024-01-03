package com.proyectoV1.reservaSalones.domain.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "salon")
public class Salon {
    @Id
    @SequenceGenerator(name = "salon_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "salon_sequence")
    @Column(nullable = false)
    private Integer id;

    @Column(name = "nombre", length = 100, nullable = false)
    private String nombre;
    @Column(name = "direccion", length = 255, nullable = false)
    private String direccion;
    @Column(name = "capacidad", nullable = false)
    private Integer capacidad;
    @Column(name = "descripcion", length = 255, nullable = false)
    private String descripcion;
    @Column(name = "tarifa", nullable = false)
    private Integer tarifa;
    @Column(name = "estado", nullable = false)
    private Integer estado;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP", nullable = false)
    private LocalDateTime created_at;
}

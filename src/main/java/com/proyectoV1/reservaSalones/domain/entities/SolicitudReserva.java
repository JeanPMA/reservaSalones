package com.proyectoV1.reservaSalones.domain.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
@Entity
@Table(name = "solicitud_reserva")
public class SolicitudReserva {
    @Id
    @SequenceGenerator(name = "solicitud_reserva_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "solicitud_reserva_sequence")
    @Column(nullable = false)
    private Integer id;

    @Column(name = "detalle", length = 255, nullable = false)
    private String detalle;
    @Column(name = "fecha_reserva", columnDefinition = "DATE", nullable = true)
    private LocalDate fecha_reserva;
    @Column(name = "fecha_emision", columnDefinition = "DATE", nullable = true)
    private LocalDate fecha_emision;
    @Column(name = "motivo", length = 100, nullable = false)
    private String motivo;

}

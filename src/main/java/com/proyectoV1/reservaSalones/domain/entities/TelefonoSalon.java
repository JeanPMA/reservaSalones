package com.proyectoV1.reservaSalones.domain.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "telefono_salon")
public class TelefonoSalon {
    @Id
    @SequenceGenerator(name = "telefono_salon_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "telefono_salon_sequence")
    @Column(nullable = false)
    private Long id;

    @Column(name = "numero", nullable = false)
    private Integer numero;
}

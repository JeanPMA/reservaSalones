package com.proyectoV1.reservaSalones.domain.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @SequenceGenerator(name = "usuario_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuario_sequence")
    @Column(nullable = false)
    private Integer id;
    @Column(name = "usuario", length = 100, nullable = false)
    private String usuario;
    @Column(name = "password", length = 100, nullable = false)
    private String password;
    @Column(name = "telefono", nullable = false)
    private String telefono;
    @Column(name = "nombre", length = 100, nullable = false)
    private String nombre;
    @Column(name = "apellido", length = 100, nullable = false)
    private String apellido;
    @Column(name = "correo", length = 255, nullable = false)
    private String correo;
    @Column(name = "estado", nullable = false)
    private Integer estado;

}

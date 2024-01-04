package com.proyectoV1.reservaSalones.domain.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "servicio")
public class Servicio {
    @Id
    @SequenceGenerator(name = "servicio_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "servicio_sequence")
    @Column(name = "servicio_id", nullable = false)
    private Integer id;

    @Column(name = "nombre", length = 100, nullable = false)
    private String nombre;

    @Column(name = "detalle", length = 255, nullable = false)
    private String detalle;

    @Column(name = "estado", nullable = false)
    private Integer estado;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP", nullable = false)
    private LocalDateTime created_at;
    @ManyToMany
    @JoinTable(name = "servicio_salon", joinColumns = @JoinColumn(name = "servicio_id", referencedColumnName = "servicio_id"),
            inverseJoinColumns = @JoinColumn(name = "salon_id", referencedColumnName = "salon_id"))
    private Set<Salon> salones = new HashSet<>();

    public Servicio() {
    }

    public Servicio(String nombre, String detalle, Integer estado, LocalDateTime created_at, Set<Salon> salones) {
        this.nombre = nombre;
        this.detalle = detalle;
        this.estado = estado;
        this.created_at = created_at;
        this.salones = salones;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public Set<Salon> getSalones() {
        return salones;
    }

    public void setSalones(Set<Salon> salones) {
        this.salones = salones;
    }

    @Override
    public String toString() {
        return "Servicio{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", detalle='" + detalle + '\'' +
                ", estado=" + estado +
                ", created_at=" + created_at +
                ", salones=" + salones +
                '}';
    }
}

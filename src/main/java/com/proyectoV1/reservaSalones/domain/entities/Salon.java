package com.proyectoV1.reservaSalones.domain.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "salon")
public class Salon {
    @Id
    @SequenceGenerator(name = "salon_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "salon_sequence")
    @Column(name = "salon_id", nullable = false)
    private Integer id;

    @Column(name = "nombre", length = 100, nullable = false)
    private String nombre;
    @Column(name = "direccion", length = 255, nullable = false)
    private String direccion;
    @Column(name = "capacidad", nullable = false)
    private Integer capacidad;
    @Column(name = "descripcion", length = 255, nullable = false)
    private String descripcion;
    @Column(name = "tarifa", length = 100, nullable = false)
    private Integer tarifa;
    @Column(name = "estado", nullable = false)
    private Integer estado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    @Column(name = "created_at", columnDefinition = "TIMESTAMP", nullable = false)
    private LocalDateTime created_at;
    @ManyToMany
    @JsonBackReference
    @JoinTable(name = "servicio_salon", joinColumns = @JoinColumn(name = "salon_id", referencedColumnName = "salon_id"),
            inverseJoinColumns = @JoinColumn(name = "servicio_id", referencedColumnName = "servicio_id"))
    private Set<Servicio> servicios = new HashSet<>();

    public Salon() {
    }

    public Salon(String nombre, String direccion, Integer capacidad, String descripcion, Integer tarifa, Integer estado, Usuario usuario, LocalDateTime created_at, Set<Servicio> servicios) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.capacidad = capacidad;
        this.descripcion = descripcion;
        this.tarifa = tarifa;
        this.estado = estado;
        this.usuario = usuario;
        this.created_at = created_at;
        this.servicios = servicios;
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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Integer getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getTarifa() {
        return tarifa;
    }

    public void setTarifa(Integer tarifa) {
        this.tarifa = tarifa;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public Set<Servicio> getServicios() {
        return servicios;
    }

    public void setServicios(Set<Servicio> servicios) {
        this.servicios = servicios;
    }

    @Override
    public String toString() {
        return "Salon{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", direccion='" + direccion + '\'' +
                ", capacidad=" + capacidad +
                ", descripcion='" + descripcion + '\'' +
                ", tarifa=" + tarifa +
                ", estado=" + estado +
                ", usuario=" + usuario +
                ", created_at=" + created_at +
                ", servicios=" + servicios +
                '}';
    }
}

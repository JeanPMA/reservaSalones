package com.proyectoV1.reservaSalones.dto;

import jakarta.persistence.Column;

import java.time.LocalDateTime;

public class TipoSolicitudReservaDTO {
    private Integer id;
    private String nombre;

    private Integer estado;
    private LocalDateTime created_at;

    public TipoSolicitudReservaDTO() {
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

    @Override
    public String toString() {
        return "TipoSolicitudReservaDTO{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", created_at=" + created_at +
                '}';
    }
}

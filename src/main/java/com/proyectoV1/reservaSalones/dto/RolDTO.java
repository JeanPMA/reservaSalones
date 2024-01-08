package com.proyectoV1.reservaSalones.dto;

import java.time.LocalDateTime;

public class RolDTO {
    private Integer id;
    private String nombre;
    private LocalDateTime created_at;

    public RolDTO() {
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

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    @Override
    public String toString() {
        return "RolDTO{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", created_at=" + created_at +
                '}';
    }
}

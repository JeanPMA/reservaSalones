package com.proyectoV1.reservaSalones.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.proyectoV1.reservaSalones.domain.entities.Salon;

public class ImagenSalonDTO {
    private Long id;
    private String nombre;
    private String imagen_id;
    private String imagen_url;
    @JsonIgnoreProperties({"direccion", "capacidad", "descripcion", "tarifa", "usuario","created_at"})
    private Salon salon;

    public ImagenSalonDTO() {
    }

    public ImagenSalonDTO(String nombre, String imagen_id, String imagen_url, Salon salon) {
        this.nombre = nombre;
        this.imagen_id = imagen_id;
        this.imagen_url = imagen_url;
        this.salon = salon;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImagen_id() {
        return imagen_id;
    }

    public void setImagen_id(String imagen_id) {
        this.imagen_id = imagen_id;
    }

    public String getImagen_url() {
        return imagen_url;
    }

    public void setImagen_url(String imagen_url) {
        this.imagen_url = imagen_url;
    }

    public Salon getSalon() {
        return salon;
    }

    public void setSalon(Salon salon) {
        this.salon = salon;
    }

    @Override
    public String toString() {
        return "ImagenSalonDTO{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", imagen_id='" + imagen_id + '\'' +
                ", imagen_url='" + imagen_url + '\'' +
                ", salon=" + salon +
                '}';
    }
}

package com.proyectoV1.reservaSalones.domain.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "imagen_salon")
public class ImagenSalon {
    @Id
    @SequenceGenerator(name = "imagen_salon_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "imagen_salon_sequence")
    @Column(nullable = false)
    private Long id;
    @Column(name = "nombre", length = 255, nullable = false)
    private String nombre;
    @Column(name = "imagen_id", length = 255, nullable = false)
    private String imagen_id;
    @Column(name = "imagen_url", length = 255, nullable = false)
    private String imagen_url;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "salon_id")
    private Salon salon;

    public ImagenSalon() {
    }

    public ImagenSalon(String nombre, String imagen_id, String imagen_url, Salon salon) {
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
        return "ImagenSalon{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", imagen_id='" + imagen_id + '\'' +
                ", imagen_url='" + imagen_url + '\'' +
                ", salon=" + salon +
                '}';
    }
}

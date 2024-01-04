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

    @Column(name = "url", length = 255, nullable = false)
    private String url;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "salon_id")
    private Salon salon;

    public ImagenSalon() {
    }

    public ImagenSalon(String url, Salon salon) {
        this.url = url;
        this.salon = salon;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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
                ", url='" + url + '\'' +
                ", salon=" + salon +
                '}';
    }
}

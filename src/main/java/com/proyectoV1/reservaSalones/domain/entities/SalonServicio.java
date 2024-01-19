package com.proyectoV1.reservaSalones.domain.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "servicio_salon")
public class SalonServicio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "salon_id")
    private Salon salon;

    @ManyToOne
    @JoinColumn(name = "servicio_id")
    private Servicio servicio;

    public SalonServicio() {
    }

    public SalonServicio(Salon salon, Servicio servicio) {
        this.salon = salon;
        this.servicio = servicio;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Salon getSalon() {
        return salon;
    }

    public void setSalon(Salon salon) {
        this.salon = salon;
    }

    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }

    @Override
    public String toString() {
        return "SalonServicio{" +
                "id=" + id +
                ", salon=" + salon +
                ", servicio=" + servicio +
                '}';
    }
}

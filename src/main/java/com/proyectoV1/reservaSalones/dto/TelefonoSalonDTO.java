package com.proyectoV1.reservaSalones.dto;

import com.proyectoV1.reservaSalones.domain.entities.Salon;

public class TelefonoSalonDTO {
    private Long id;
    private Integer numero;
    private Salon salon;

    public TelefonoSalonDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Salon getSalon() {
        return salon;
    }

    public void setSalon(Salon salon) {
        this.salon = salon;
    }

    @Override
    public String toString() {
        return "TelefonoDTO{" +
                "id=" + id +
                ", numero=" + numero +
                ", salon=" + salon +
                '}';
    }
}

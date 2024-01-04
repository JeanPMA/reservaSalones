package com.proyectoV1.reservaSalones.domain.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "telefono_salon")
public class TelefonoSalon {
    @Id
    @SequenceGenerator(name = "telefono_salon_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "telefono_salon_sequence")
    @Column(nullable = false)
    private Long id;

    @Column(name = "numero", nullable = false)
    private Integer numero;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "salon_id")
    private Salon salon;

    public TelefonoSalon() {
    }

    public TelefonoSalon(Integer numero, Salon salon) {
        this.numero = numero;
        this.salon = salon;
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
        return "TelefonoSalon{" +
                "id=" + id +
                ", numero=" + numero +
                ", salon=" + salon +
                '}';
    }
}

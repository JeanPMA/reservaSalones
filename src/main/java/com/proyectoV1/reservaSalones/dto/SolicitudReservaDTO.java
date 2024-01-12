package com.proyectoV1.reservaSalones.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.proyectoV1.reservaSalones.domain.entities.Salon;
import com.proyectoV1.reservaSalones.domain.entities.TipoSolicitudReserva;
import com.proyectoV1.reservaSalones.domain.entities.Usuario;

import java.time.LocalDate;

public class SolicitudReservaDTO {
    private Long id;
    private String detalle;
    private LocalDate fecha_reserva;
    private LocalDate fecha_emision;
    private String motivo;
    @JsonIgnoreProperties({"usuario","created_at"})
    private Salon salon;
    @JsonIgnoreProperties({"usuario","password","correo","rol"})
    private Usuario usuario;
    @JsonIgnoreProperties({"created_at"})
    private TipoSolicitudReserva tipoSR;

    public SolicitudReservaDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public LocalDate getFecha_reserva() {
        return fecha_reserva;
    }

    public void setFecha_reserva(LocalDate fecha_reserva) {
        this.fecha_reserva = fecha_reserva;
    }

    public LocalDate getFecha_emision() {
        return fecha_emision;
    }

    public void setFecha_emision(LocalDate fecha_emision) {
        this.fecha_emision = fecha_emision;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Salon getSalon() {
        return salon;
    }

    public void setSalon(Salon salon) {
        this.salon = salon;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public TipoSolicitudReserva getTipoSR() {
        return tipoSR;
    }

    public void setTipoSR(TipoSolicitudReserva tipoSR) {
        this.tipoSR = tipoSR;
    }
}

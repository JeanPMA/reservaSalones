package com.proyectoV1.reservaSalones.domain.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
@Entity
@Table(name = "solicitud_reserva")
public class SolicitudReserva {
    @Id
    @SequenceGenerator(name = "solicitud_reserva_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "solicitud_reserva_sequence")
    @Column(nullable = false)
    private Integer id;

    @Column(name = "detalle", length = 255, nullable = false)
    private String detalle;
    @Column(name = "fecha_reserva", columnDefinition = "DATE", nullable = false)
    private LocalDate fecha_reserva;
    @Column(name = "fecha_emision", columnDefinition = "DATE", nullable = false)
    private LocalDate fecha_emision;
    @Column(name = "motivo", length = 100, nullable = false)
    private String motivo;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "salon_id")
    private Salon salon;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipoSR_id")
    private TipoSolicitudReserva tipoSR;

    public SolicitudReserva() {
    }

    public SolicitudReserva(String detalle, LocalDate fecha_reserva, LocalDate fecha_emision, String motivo, Salon salon, Usuario usuario, TipoSolicitudReserva tipoSR) {
        this.detalle = detalle;
        this.fecha_reserva = fecha_reserva;
        this.fecha_emision = fecha_emision;
        this.motivo = motivo;
        this.salon = salon;
        this.usuario = usuario;
        this.tipoSR = tipoSR;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    @Override
    public String toString() {
        return "SolicitudReserva{" +
                "id=" + id +
                ", detalle='" + detalle + '\'' +
                ", fecha_reserva=" + fecha_reserva +
                ", fecha_emision=" + fecha_emision +
                ", motivo='" + motivo + '\'' +
                ", salon=" + salon +
                ", usuario=" + usuario +
                ", tipoSR=" + tipoSR +
                '}';
    }
}

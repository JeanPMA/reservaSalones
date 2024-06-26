package com.proyectoV1.reservaSalones.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.proyectoV1.reservaSalones.domain.entities.Servicio;
import com.proyectoV1.reservaSalones.domain.entities.Usuario;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class SalonAvgDTO {
    private Integer id;
    private String nombre;
    private String direccion;
    private Integer capacidad;
    private String descripcion;
    private String banner_id;
    private String banner_url;
    private Integer tarifa;
    private Integer estado;
    @JsonIgnoreProperties({"username","password","telefono","correo","rol"})
    private Usuario usuario;
    private LocalDateTime created_at;
    @JsonIgnoreProperties({"detalle","created_at","salones"})
    private Set<Servicio> servicios = new HashSet<>();
    private double avgPuntuacion;
    public SalonAvgDTO() {
    }

    public SalonAvgDTO(Integer id, String nombre, String direccion, Integer capacidad, String descripcion, String banner_id, String banner_url, Integer tarifa, Integer estado, Usuario usuario, LocalDateTime created_at, Set<Servicio> servicios, double avgPuntuacion) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.capacidad = capacidad;
        this.descripcion = descripcion;
        this.banner_id = banner_id;
        this.banner_url = banner_url;
        this.tarifa = tarifa;
        this.estado = estado;
        this.usuario = usuario;
        this.created_at = created_at;
        this.servicios = servicios;
        this.avgPuntuacion = avgPuntuacion;
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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Integer getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getBanner_id() {
        return banner_id;
    }

    public void setBanner_id(String banner_id) {
        this.banner_id = banner_id;
    }

    public String getBanner_url() {
        return banner_url;
    }

    public void setBanner_url(String banner_url) {
        this.banner_url = banner_url;
    }

    public Integer getTarifa() {
        return tarifa;
    }

    public void setTarifa(Integer tarifa) {
        this.tarifa = tarifa;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public Set<Servicio> getServicios() {
        return servicios;
    }

    public void setServicios(Set<Servicio> servicios) {
        this.servicios = servicios;
    }

    public double getAvgPuntuacion() {
        return avgPuntuacion;
    }

    public void setAvgPuntuacion(double avgPuntuacion) {
        this.avgPuntuacion = avgPuntuacion;
    }
}

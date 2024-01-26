package com.proyectoV1.reservaSalones.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.proyectoV1.reservaSalones.domain.entities.Rol;

public class UsuarioDTO {
    private Long id;
    private String username;
    private String password;
    private Integer telefono;
    private String nombre;
    private String apellido;
    private String correo;
    private Integer estado;
    @JsonIgnoreProperties({"created_at"})
    private Rol rol;

    public UsuarioDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    @Override
    public String toString() {
        return "UsuarioDTO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", telefono='" + telefono + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", correo='" + correo + '\'' +
                ", estado=" + estado +
                ", rol=" + rol +
                '}';
    }
}

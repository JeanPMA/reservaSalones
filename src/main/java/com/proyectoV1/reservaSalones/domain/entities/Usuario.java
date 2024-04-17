package com.proyectoV1.reservaSalones.domain.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @SequenceGenerator(name = "usuario_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuario_sequence")
    @Column(nullable = false)
    private Long id;
    @Column(name = "username", length = 100, nullable = false)
    private String username;
    @Column(name = "password", length = 100, nullable = false)
    private String password;
    @Column(name = "telefono", nullable = false)
    private Integer telefono;
    @Column(name = "nombre", length = 100, nullable = false)
    private String nombre;
    @Column(name = "apellido", length = 100, nullable = false)
    private String apellido;
    @Column(name = "correo", length = 255, nullable = false)
    private String correo;
    @Column(name = "estado", nullable = false)
    private Integer estado;
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "rol_id")
    private Rol rol;

    public Usuario() {
    }

    public Usuario(String username, String password, Integer telefono, String nombre, String apellido, String correo, Integer estado, Rol rol) {
        this.username = username;
        this.password = password;
        this.telefono = telefono;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.estado = estado;
        this.rol = rol;
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
        return "Usuario{" +
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

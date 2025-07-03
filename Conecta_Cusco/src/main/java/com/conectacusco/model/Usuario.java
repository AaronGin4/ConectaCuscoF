package com.conectacusco.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "usuarios")
@Data
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre_usuario", unique = true, nullable = false)
    private String nombreUsuario;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String contrasena; // La contraseña se guardará encriptada

    @Column(name = "nombre_completo")
    private String nombreCompleto;

    private String telefono;
    private String direccion;

    @Column(name = "url_foto_perfil")
    private String urlFotoPerfil;

    @ManyToOne
    @JoinColumn(name = "id_rol", nullable = false)
    private Role rol;
}
package com.conectacusco.dto;

import lombok.Data;
import com.conectacusco.model.Role;

@Data
public class RegisterRequest {
    private String nombreUsuario;
    private String email;
    private String contrasena;
    private String nombreCompleto;
    private String telefono;
    private String direccion;
    private String urlFotoPerfil;
    private Role.ERole rol; // Para especificar si es TRABAJADOR o EMPLEADOR
}
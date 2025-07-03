package com.conectacusco.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "roles")
@Data // Genera getters, setters, toString, equals y hashCode
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(length = 50)
    private ERole nombre;

    public enum ERole {
        TRABAJADOR,
        EMPLEADOR
    }
}
package com.conectacusco.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "categorias_servicio")
@Data
public class CategoriaServicio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String nombre;

    private String descripcion;
}
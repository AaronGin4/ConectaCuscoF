package com.conectacusco.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "publicaciones_servicio")
@Data
public class PublicacionServicio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String descripcion;

    private Double precio;

    private String ubicacion;

    @Column(name = "fecha_publicacion", updatable = false)
    private LocalDateTime fechaPublicacion = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_categoria", nullable = false)
    private CategoriaServicio categoria;
}
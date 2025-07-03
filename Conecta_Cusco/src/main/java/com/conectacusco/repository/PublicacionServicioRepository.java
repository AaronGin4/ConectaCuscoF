package com.conectacusco.repository;

import com.conectacusco.model.PublicacionServicio;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PublicacionServicioRepository extends JpaRepository<PublicacionServicio, Long> {
    List<PublicacionServicio> findByUsuarioId(Long userId);
    List<PublicacionServicio> findByCategoriaNombreContainingIgnoreCase(String categoryName);
    List<PublicacionServicio> findByTituloContainingIgnoreCaseOrDescripcionContainingIgnoreCase(String title, String description);
}
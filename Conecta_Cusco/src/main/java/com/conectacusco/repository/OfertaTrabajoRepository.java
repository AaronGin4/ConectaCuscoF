package com.conectacusco.repository;

import com.conectacusco.model.OfertaTrabajo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OfertaTrabajoRepository extends JpaRepository<OfertaTrabajo, Long> {
    List<OfertaTrabajo> findByUsuarioId(Long userId);
    List<OfertaTrabajo> findByCategoriaNombreContainingIgnoreCase(String categoryName);
    List<OfertaTrabajo> findByTituloContainingIgnoreCaseOrDescripcionContainingIgnoreCase(String title, String description);
}
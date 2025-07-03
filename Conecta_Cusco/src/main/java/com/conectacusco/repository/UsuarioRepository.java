package com.conectacusco.repository;

import com.conectacusco.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByNombreUsuario(String nombreUsuario);
    Optional<Usuario> findByEmail(String email);
    Boolean existsByNombreUsuario(String nombreUsuario);
    Boolean existsByEmail(String email);
}
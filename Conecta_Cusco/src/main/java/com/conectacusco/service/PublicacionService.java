package com.conectacusco.service;

import com.conectacusco.model.PublicacionServicio;
import com.conectacusco.model.OfertaTrabajo;
import com.conectacusco.model.Usuario;
import com.conectacusco.model.CategoriaServicio;
import com.conectacusco.repository.PublicacionServicioRepository;
import com.conectacusco.repository.OfertaTrabajoRepository;
import com.conectacusco.repository.UsuarioRepository;
import com.conectacusco.repository.CategoriaServicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PublicacionService {

    @Autowired
    private PublicacionServicioRepository publicacionServicioRepository;

    @Autowired
    private OfertaTrabajoRepository ofertaTrabajoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CategoriaServicioRepository categoriaServicioRepository;

    // Métodos para PublicacionServicio
    public List<PublicacionServicio> getAllPublicacionesServicio() {
        return publicacionServicioRepository.findAll();
    }

    public Optional<PublicacionServicio> getPublicacionServicioById(Long id) {
        return publicacionServicioRepository.findById(id);
    }

    public PublicacionServicio createPublicacionServicio(PublicacionServicio publicacionServicio, Long userId, Integer categoriaId) {
        Usuario usuario = usuarioRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + userId));
        CategoriaServicio categoria = categoriaServicioRepository.findById(categoriaId)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada con ID: " + categoriaId));
        publicacionServicio.setUsuario(usuario);
        publicacionServicio.setCategoria(categoria);
        return publicacionServicioRepository.save(publicacionServicio);
    }

    public PublicacionServicio updatePublicacionServicio(Long id, PublicacionServicio publicacionDetails) {
        PublicacionServicio publicacion = publicacionServicioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Publicación de servicio no encontrada con ID: " + id));
        publicacion.setTitulo(publicacionDetails.getTitulo());
        publicacion.setDescripcion(publicacionDetails.getDescripcion());
        publicacion.setPrecio(publicacionDetails.getPrecio());
        publicacion.setUbicacion(publicacionDetails.getUbicacion());
        // No actualizamos usuario ni categoría directamente aquí, para cambios se debería manejar lógica específica
        return publicacionServicioRepository.save(publicacion);
    }

    public void deletePublicacionServicio(Long id) {
        publicacionServicioRepository.deleteById(id);
    }

    public List<PublicacionServicio> searchPublicacionesServicio(String query) {
        return publicacionServicioRepository.findByTituloContainingIgnoreCaseOrDescripcionContainingIgnoreCase(query, query);
    }

    public List<PublicacionServicio> getPublicacionesServicioByCategoria(String categoryName) {
        return publicacionServicioRepository.findByCategoriaNombreContainingIgnoreCase(categoryName);
    }

    // Métodos para OfertaTrabajo
    public List<OfertaTrabajo> getAllOfertasTrabajo() {
        return ofertaTrabajoRepository.findAll();
    }

    public Optional<OfertaTrabajo> getOfertaTrabajoById(Long id) {
        return ofertaTrabajoRepository.findById(id);
    }

    public OfertaTrabajo createOfertaTrabajo(OfertaTrabajo ofertaTrabajo, Long userId, Integer categoriaId) {
        Usuario usuario = usuarioRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + userId));
        CategoriaServicio categoria = categoriaServicioRepository.findById(categoriaId)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada con ID: " + categoriaId));
        ofertaTrabajo.setUsuario(usuario);
        ofertaTrabajo.setCategoria(categoria);
        return ofertaTrabajoRepository.save(ofertaTrabajo);
    }

    public OfertaTrabajo updateOfertaTrabajo(Long id, OfertaTrabajo ofertaDetails) {
        OfertaTrabajo oferta = ofertaTrabajoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Oferta de trabajo no encontrada con ID: " + id));
        oferta.setTitulo(ofertaDetails.getTitulo());
        oferta.setDescripcion(ofertaDetails.getDescripcion());
        oferta.setPresupuesto(ofertaDetails.getPresupuesto());
        oferta.setUbicacion(ofertaDetails.getUbicacion());
        return ofertaTrabajoRepository.save(oferta);
    }

    public void deleteOfertaTrabajo(Long id) {
        ofertaTrabajoRepository.deleteById(id);
    }

    public List<OfertaTrabajo> searchOfertasTrabajo(String query) {
        return ofertaTrabajoRepository.findByTituloContainingIgnoreCaseOrDescripcionContainingIgnoreCase(query, query);
    }

    public List<OfertaTrabajo> getOfertasTrabajoByCategoria(String categoryName) {
        return ofertaTrabajoRepository.findByCategoriaNombreContainingIgnoreCase(categoryName);
    }
}
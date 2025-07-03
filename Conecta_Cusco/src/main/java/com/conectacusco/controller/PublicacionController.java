package com.conectacusco.controller;

import com.conectacusco.model.PublicacionServicio;
import com.conectacusco.model.OfertaTrabajo;
import com.conectacusco.service.PublicacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600) // Permitir CORS desde Angular
@RestController
@RequestMapping("/api/publicaciones")
public class PublicacionController {

    @Autowired
    private PublicacionService publicacionService;

    // Endpoints para PublicacionServicio
    @GetMapping("/servicios")
    public List<PublicacionServicio> getAllPublicacionesServicio() {
        return publicacionService.getAllPublicacionesServicio();
    }

    @GetMapping("/servicios/{id}")
    public ResponseEntity<PublicacionServicio> getPublicacionServicioById(@PathVariable Long id) {
        return publicacionService.getPublicacionServicioById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/servicios/crear/{userId}/{categoriaId}")
    public PublicacionServicio createPublicacionServicio(@PathVariable Long userId, @PathVariable Integer categoriaId, @RequestBody PublicacionServicio publicacionServicio) {
        return publicacionService.createPublicacionServicio(publicacionServicio, userId, categoriaId);
    }

    @PutMapping("/servicios/actualizar/{id}")
    public ResponseEntity<PublicacionServicio> updatePublicacionServicio(@PathVariable Long id, @RequestBody PublicacionServicio publicacionDetails) {
        return ResponseEntity.ok(publicacionService.updatePublicacionServicio(id, publicacionDetails));
    }

    @DeleteMapping("/servicios/eliminar/{id}")
    public ResponseEntity<?> deletePublicacionServicio(@PathVariable Long id) {
        publicacionService.deletePublicacionServicio(id);
        return ResponseEntity.ok("Publicación de servicio eliminada exitosamente.");
    }

    @GetMapping("/servicios/buscar")
    public List<PublicacionServicio> searchPublicacionesServicio(@RequestParam String query) {
        return publicacionService.searchPublicacionesServicio(query);
    }

    @GetMapping("/servicios/categoria")
    public List<PublicacionServicio> getPublicacionesServicioByCategoria(@RequestParam String category) {
        return publicacionService.getPublicacionesServicioByCategoria(category);
    }


    // Endpoints para OfertaTrabajo
    @GetMapping("/ofertas")
    public List<OfertaTrabajo> getAllOfertasTrabajo() {
        return publicacionService.getAllOfertasTrabajo();
    }

    @GetMapping("/ofertas/{id}")
    public ResponseEntity<OfertaTrabajo> getOfertaTrabajoById(@PathVariable Long id) {
        return publicacionService.getOfertaTrabajoById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/ofertas/crear/{userId}/{categoriaId}")
    public OfertaTrabajo createOfertaTrabajo(@PathVariable Long userId, @PathVariable Integer categoriaId, @RequestBody OfertaTrabajo ofertaTrabajo) {
        return publicacionService.createOfertaTrabajo(ofertaTrabajo, userId, categoriaId);
    }

    @PutMapping("/ofertas/actualizar/{id}")
    public ResponseEntity<OfertaTrabajo> updateOfertaTrabajo(@PathVariable Long id, @RequestBody OfertaTrabajo ofertaDetails) {
        return ResponseEntity.ok(publicacionService.updateOfertaTrabajo(id, ofertaDetails));
    }

    @DeleteMapping("/ofertas/eliminar/{id}")
    public ResponseEntity<?> deleteOfertaTrabajo(@PathVariable Long id) {
        publicacionService.deleteOfertaTrabajo(id);
        return ResponseEntity.ok("Oferta de trabajo eliminada exitosamente.");
    }

    @GetMapping("/ofertas/buscar")
    public List<OfertaTrabajo> searchOfertasTrabajo(@RequestParam String query) {
        return publicacionService.searchOfertasTrabajo(query);
    }

    @GetMapping("/ofertas/categoria")
    public List<OfertaTrabajo> getOfertasTrabajoByCategoria(@RequestParam String category) {
        return publicacionService.getOfertasTrabajoByCategoria(category);
    }
}
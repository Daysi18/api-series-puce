package com.puce.series.api_series.controller;

import com.puce.series.api_series.models.Serie;
import com.puce.series.api_series.service.SerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/series")
public class SerieController {

    @Autowired
    private SerieService serieService;

    // GET /api/series
    @GetMapping
    public List<Serie> listar() {
        return serieService.listarTodas();
    }

    // GET /api/series/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Serie> buscar(@PathVariable Long id) {
        return serieService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /api/series
    @PostMapping
    public Serie crear(@RequestBody Serie serie) {
        return serieService.guardar(serie);
    }

    // PUT /api/series/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Serie> actualizar(@PathVariable Long id, @RequestBody Serie detalles) {
        return serieService.buscarPorId(id)
                .map(serieExistente -> {
                    serieExistente.setTitulo(detalles.getTitulo());
                    serieExistente.setPlataforma(detalles.getPlataforma());
                    serieExistente.setCalificacion(detalles.getCalificacion());
                    return ResponseEntity.ok(serieService.guardar(serieExistente));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE /api/series/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (serieService.buscarPorId(id).isPresent()) {
            serieService.eliminar(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
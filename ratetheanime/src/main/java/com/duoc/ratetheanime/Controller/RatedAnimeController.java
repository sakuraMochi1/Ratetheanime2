package com.duoc.ratetheanime.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.slf4j.Slf4j;
import com.duoc.ratetheanime.dto.RatedAnimeDTO;

import com.duoc.ratetheanime.model.RatedAnime;
import com.duoc.ratetheanime.service.RatedAnimeService;

import jakarta.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/api/ratedanimes")

public class RatedAnimeController {
    
    @Autowired
    private RatedAnimeService ratedAnimeService;

    @GetMapping
    public ResponseEntity<List<RatedAnimeDTO>> listarRatedAnimes() {
        log.info("GET /api/ratedanimes - Listando todos los animes calificados");
        return ResponseEntity.ok(ratedAnimeService.getRatedAnimes());
    }

    @PostMapping
    public ResponseEntity<RatedAnimeDTO> agregarRatedAnime(@Valid @RequestBody RatedAnime ratedAnime) {
        log.info("POST /api/ratedanimes - creando un anime para calificar: {}", ratedAnime.getAnimeTitulo());
        return ResponseEntity.status(HttpStatus.CREATED).body(ratedAnimeService.saveRatedAnime(ratedAnime));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RatedAnimeDTO> buscarRatedAnime(@PathVariable int id) {
        log.info("GET /api/ratedanimes/{} - Buscando calificacion de anime", id);
        RatedAnimeDTO dto = ratedAnimeService.getRatedAnimeById(id);
        if (dto == null) {
            log.warn("GET /api/ratedanimes/{} - Calificacion de anime no encontrada", id);
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RatedAnimeDTO> actualizarRatedAnime(@PathVariable int id, @Valid @RequestBody RatedAnime ratedAnime){
        log.info("PUT /api/ratedanimes/{} - Actualizando datos del anime", id);
        ratedAnime.setId(id);
        RatedAnimeDTO actualizado = ratedAnimeService.updateRatedAnime(ratedAnime);
        if (actualizado == null) {
            log.warn("PUT /api/ratedanimes/{} - Error al actualizar calificacion de anime", id);
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarRatedAnime(@PathVariable int id) {
        log.info("DELETE /api/ratedanimes/{} - Eliminando calificacion", id);
        ratedAnimeService.deleteRatedAnime(id);
        return ResponseEntity.noContent().build();
    }

}
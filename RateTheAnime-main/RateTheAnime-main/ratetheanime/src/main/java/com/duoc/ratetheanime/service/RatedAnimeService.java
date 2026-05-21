package com.duoc.ratetheanime.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.duoc.ratetheanime.dto.RatedAnimeDTO;
import com.duoc.ratetheanime.model.RatedAnime;
import com.duoc.ratetheanime.repository.RatedAnimeRepository;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class RatedAnimeService {
    
    @Autowired
    private RatedAnimeRepository ratedAnimeRepository;

    private RatedAnimeDTO convertirADTO(RatedAnime anime) {
        RatedAnimeDTO dto = new RatedAnimeDTO();
        dto.setAnimeTitulo(anime.getAnimeTitulo());
        dto.setCalificacion(anime.getCalificacion());
        dto.setComentario(anime.getComentario());
        dto.setSinopsis(anime.getSinopsis());
        dto.setEstudio(anime.getEstudio());
        return dto;
    }

    public List<RatedAnimeDTO> getRatedAnimes() {
        log.info("Obteniendo todos los animes calificados");
        return ratedAnimeRepository.findAll().stream().map(this::convertirADTO).collect(Collectors.toList());
    }

    public RatedAnimeDTO saveRatedAnime(RatedAnime ratedAnime) {
        log.info("guardando nueva calificacion para: {}", ratedAnime.getAnimeTitulo());
        RatedAnime animeGuardado = ratedAnimeRepository.save(ratedAnime);
        return convertirADTO(animeGuardado);
    }

    public RatedAnimeDTO getRatedAnimeById(Integer id) {
        log.info("Buscando calificacion de anime con ID: {}", id);
        RatedAnime anime = ratedAnimeRepository.findById(id).orElse(null);
        return (anime != null) ? convertirADTO(anime) : null;
    }

public RatedAnimeDTO updateRatedAnime(RatedAnime ratedAnime){
        log.info("Actualizando calificación con ID: {}", ratedAnime.getId());
        if (!ratedAnimeRepository.existsById(ratedAnime.getId())) {
            log.warn("No se encontró el registro con ID: {} para actualizar", ratedAnime.getId());
            return null;
        }
        RatedAnime actualizado = ratedAnimeRepository.save(ratedAnime);
        return convertirADTO(actualizado);
    }

    public void deleteRatedAnime(Integer id) {
        log.info("Eliminando calificación con ID: {}", id);
        ratedAnimeRepository.deleteById(id);
    }
}

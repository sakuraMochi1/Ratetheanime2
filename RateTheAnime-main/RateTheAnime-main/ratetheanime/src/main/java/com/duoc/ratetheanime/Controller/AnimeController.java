package com.duoc.ratetheanime.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.duoc.ratetheanime.service.MyAnimeListService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1/anime")
public class AnimeController {

    private final MyAnimeListService malService;

    public AnimeController(MyAnimeListService malService) {
        this.malService = malService;
    }

    @GetMapping("/buscar")
    public String buscarAnime(@RequestParam String nombre) {
        log.info("GET /api/v1/anime/buscar - Buscando anime: {}", nombre);
        return malService.buscarAnime(nombre);
    }
}

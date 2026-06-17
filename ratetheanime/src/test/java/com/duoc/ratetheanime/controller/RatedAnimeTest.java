package com.duoc.ratetheanime.controller;

import com.duoc.ratetheanime.Controller.RatedAnimeController;
import com.duoc.ratetheanime.dto.RatedAnimeDTO;
import com.duoc.ratetheanime.model.RatedAnime;
import com.duoc.ratetheanime.service.RatedAnimeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)

class RatedAnimeTest {

    @Mock
    private RatedAnimeService ratedAnimeService;

    @InjectMocks
    private RatedAnimeController ratedAnimeController;

    @Test
    void crear_anime_puntuado(){

        RatedAnimeDTO ratedAnimeDTO = RatedAnimeDTO.builder()
            .animeTitulo("Steins;Gate")
            .calificacion("9.2")
            .comentario("entry_test")
            .sinopsis("test")
            .estudio("White Fox")
            .build();
    }
    
}

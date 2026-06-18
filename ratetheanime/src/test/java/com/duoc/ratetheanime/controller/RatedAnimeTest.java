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

@Test
void obtener_anime_por_id_exitoso(){
    int idBuscar = 1;
    
    RatedAnimeDTO ratedAnimeDTO = RatedAnimeDTO.builder()
            .animeTitulo("Attack on Titan")
            .calificacion("9.5")
            .comentario("Excelente adaptacion")
            .sinopsis("Humanos contra titanes")
            .estudio("MAPPA")
            .build();

    when(ratedAnimeService.getRatedAnimeById(idBuscar)).thenReturn(ratedAnimeDTO);

    ResponseEntity<RatedAnimeDTO> respuesta = ratedAnimeController.buscarRatedAnime(idBuscar);

    assertNotNull(respuesta);
    assertEquals(HttpStatus.OK, respuesta.getStatusCode()); 
    assertEquals("Attack on Titan", respuesta.getBody().getAnimeTitulo());

    }

    @Test
    void obtener_anime_por_id_no_encontrado() {

    int idInexistente = 999;
    
    when(ratedAnimeService.getRatedAnimeById(idInexistente)).thenReturn(null);

    ResponseEntity<RatedAnimeDTO> respuesta = ratedAnimeController.buscarRatedAnime(idInexistente);

    assertNotNull(respuesta);

    assertEquals(HttpStatus.NOT_FOUND, respuesta.getStatusCode()); 

    assertNull(respuesta.getBody());

    }

    @Test
    void eliminar_anime_puntuado_exitoso() {

    int idEliminar = 1;

    ResponseEntity<Void> respuesta = ratedAnimeController.eliminarRatedAnime(idEliminar);

    assertNotNull(respuesta);

    assertEquals(HttpStatus.NO_CONTENT, respuesta.getStatusCode());
}
  
}

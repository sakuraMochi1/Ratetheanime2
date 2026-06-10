package com.duoc.ratetheanime.Controller;

import com.duoc.ratetheanime.model.Usuario;
import com.duoc.ratetheanime.model.RatedAnime;
import com.duoc.ratetheanime.service.RatedAnimeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendsWith(MockitoExtension.class)
class RatedAnimeControllerTest {

    @Mock
    private RatedAnimeService ratedAnimeService;

    @InjectMocks
    private RatedAnimeController ratedAnimeController;

    @Test
    void crearRatedAnime_retorna201_cuandoExisteUsuario() {

        Usuario usuario = new Usuario(1, "juanperez", "Juan Pérez", "juan.perez@example.com");
        RatedAnime ratedAnime = new RatedAnime(1, usuario, "Naruto", 10);


        when(ratedAnimeService.saveRatedAnime(ratedAnime)).thenReturn(ratedAnime);


        var respuesta = ratedAnimeController.agregarRatedAnime(ratedAnime);

        assertNotNull(respuesta);


        assertEquals(HttpStatus.CREATED, respuesta.getStatusCode());

        var body = respuesta.getBody();
        assertNotNull(body);

        assertEquals("Naruto", body.getTitulo());

    }



}

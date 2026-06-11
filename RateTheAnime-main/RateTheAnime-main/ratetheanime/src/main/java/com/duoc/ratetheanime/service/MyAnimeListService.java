package com.duoc.ratetheanime.service;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;


@Service
public class MyAnimeListService {

    private final WebClient malWebClient;
    private final String BEARER_TOKEN = "Bearer TU_TOKEN_DE_ACCESO"; // Obtenido vía OAuth2/PKCE

    public MyAnimeListService(WebClient malWebClient) {
        this.malWebClient = malWebClient;
    }

    // Ejemplo para buscar anime
    public String buscarAnime(String query) {
        return malWebClient.get()
                .uri(uriBuilder -> uriBuilder.path("/anime")
                        .queryParam("q", query)
                        .queryParam("limit", 5)
                        .build())
                .header("Authorization", BEARER_TOKEN)
                .retrieve()
                .bodyToMono(String.class)
                .block(); // .block() convierte la petición reactiva a sincrónica
    }
}

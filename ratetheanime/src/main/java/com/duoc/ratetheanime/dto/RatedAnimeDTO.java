package com.duoc.ratetheanime.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor

public class RatedAnimeDTO {
    private String animeTitulo;
    private String calificacion;
    private String comentario;
    private String sinopsis;
    private String estudio;
}
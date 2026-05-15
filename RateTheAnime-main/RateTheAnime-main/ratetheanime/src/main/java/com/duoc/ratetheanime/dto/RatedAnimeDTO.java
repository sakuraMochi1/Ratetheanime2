package com.duoc.ratetheanime.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
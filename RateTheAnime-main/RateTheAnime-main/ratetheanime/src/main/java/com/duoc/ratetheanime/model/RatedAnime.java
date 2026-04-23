package com.duoc.ratetheanime.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "rated_animes")

public class RatedAnime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    private String animeTitulo;

    @NotNull
    private String calificacion;

    @NotNull
    private String comentario;

    @NotNull
    private String sinopsis;

    // Falta funcion para incluir foto de portada del anime

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    
}

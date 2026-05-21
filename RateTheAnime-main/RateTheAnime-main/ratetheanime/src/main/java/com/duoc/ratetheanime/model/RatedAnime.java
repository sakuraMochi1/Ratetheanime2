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

    @NotBlank
    private String calificacion;

    @NotBlank
    private String comentario;

    @NotBlank
    @Column(length = 1500)
    private String sinopsis;

    @NotBlank
    private String estudio;

    // Falta funcion para incluir foto de portada del anime

    @OneToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    
}

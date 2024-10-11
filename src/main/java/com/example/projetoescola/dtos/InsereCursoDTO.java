package com.example.projetoescola.dtos;

public record InsereCursoDTO(
        Long id,

        String nome,

        Integer cargaHoraria,

        Integer categoriaCursoId
) {
}

package com.example.projetoescola.dtos;

import lombok.*;

@Builder
public record CursoDTO(
        Long id,

        String nome,

        Integer cargaHoraria,

        Integer categoriaCursoId,

        String categoriaCursoNome
) {
}

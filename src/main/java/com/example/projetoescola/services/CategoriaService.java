package com.example.projetoescola.services;

import com.example.projetoescola.dtos.InsereCategoriaDTO;
import com.example.projetoescola.models.CategoriaCurso;

public interface CategoriaService {

    CategoriaCurso insereCategoria(InsereCategoriaDTO categoriaDTO);
}

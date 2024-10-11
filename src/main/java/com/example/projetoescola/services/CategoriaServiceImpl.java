package com.example.projetoescola.services;

import com.example.projetoescola.dtos.InsereCategoriaDTO;
import com.example.projetoescola.models.CategoriaCurso;
import com.example.projetoescola.repositories.CategoriaCursoRepository;

public class CategoriaServiceImpl implements  CategoriaService {
    private CategoriaCursoRepository categoriaCursoRepository;

    public CategoriaServiceImpl(CategoriaCursoRepository categoriaCursoRepository) {
        this.categoriaCursoRepository = categoriaCursoRepository;
    }

    @Override
    public CategoriaCurso insereCategoria(InsereCategoriaDTO categoriaDTO) {
        CategoriaCurso categoria = new CategoriaCurso(categoriaDTO.nome());
        return categoriaCursoRepository.save(categoria);
    }
}

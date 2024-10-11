package com.example.projetoescola.services;


import com.example.projetoescola.dtos.AtualizaCursoReqDTO;
import com.example.projetoescola.dtos.CursoDTO;
import com.example.projetoescola.dtos.InsereCursoDTO;
import com.example.projetoescola.models.Curso;

import java.util.List;

public interface CursoService {

    Curso insereCurso(InsereCursoDTO cursoDTO);

    List<CursoDTO> listarTodosCursos();

    CursoDTO buscaCursoPorId(Long id);

    void excluiCurso(Long id);

    CursoDTO atualizaCurso(Long id, AtualizaCursoReqDTO atualizaCursoReqDTO);
}

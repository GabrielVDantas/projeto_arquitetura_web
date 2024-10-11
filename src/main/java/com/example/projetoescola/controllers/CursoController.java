package com.example.projetoescola.controllers;

import com.example.projetoescola.dtos.AtualizaCursoReqDTO;
import com.example.projetoescola.dtos.CursoDTO;
import com.example.projetoescola.dtos.InsereCursoDTO;
import com.example.projetoescola.services.CursoService;
import org.springframework.web.bind.annotation.*;

import com.example.projetoescola.models.Curso;
import com.example.projetoescola.repositories.CursoRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api/curso")
public class CursoController {
    @Autowired
    private CursoService cursoService;

    @PutMapping("{id}")
    public CursoDTO alterar(@PathVariable Long id, AtualizaCursoReqDTO atualizaCursoReqDTO) {
        return cursoService.atualizaCurso(id, atualizaCursoReqDTO);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCursoPorId(@PathVariable Long id) {
        cursoService.excluiCurso(id);
    }

    @GetMapping("{id}")
    public CursoDTO getCursoPeloId(@PathVariable Long id) {
        return cursoService.buscaCursoPorId(id);
    }

    @GetMapping()
    public List<CursoDTO> getCursos() {
        return cursoService.listarTodosCursos();
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void postCurso(@RequestBody InsereCursoDTO cursoDTO) {
        cursoService.insereCurso(cursoDTO);
    }

}

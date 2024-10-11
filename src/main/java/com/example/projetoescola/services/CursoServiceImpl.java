package com.example.projetoescola.services;

import com.example.projetoescola.dtos.AtualizaCursoReqDTO;
import com.example.projetoescola.dtos.CursoDTO;
import com.example.projetoescola.dtos.InsereCursoDTO;
import com.example.projetoescola.exceptions.RegraNegocioException;
import com.example.projetoescola.models.CategoriaCurso;
import com.example.projetoescola.models.Curso;
import com.example.projetoescola.repositories.CategoriaCursoRepository;
import com.example.projetoescola.repositories.CursoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CursoServiceImpl implements CursoService {
    private CursoRepository cursoRepository;

    private CategoriaCursoRepository categoriaCursoRepository;

    public CursoServiceImpl(CursoRepository cursoRepository, CategoriaCursoRepository categoriaCursoRepository) {
        this.cursoRepository = cursoRepository;
        this.categoriaCursoRepository = categoriaCursoRepository;
    }

    @Override
    public Curso insereCurso(InsereCursoDTO cursoDTO) {
        CategoriaCurso categoriaDoCurso = categoriaCursoRepository.findById(cursoDTO.categoriaCursoId())
                .orElseThrow(
                        () -> new RegraNegocioException("Categoria não encontrada")
                );
        Curso curso = new Curso(cursoDTO.id(), cursoDTO.nome(), cursoDTO.cargaHoraria(), categoriaDoCurso);

        return cursoRepository.save(curso);
    }

    @Override
    public List<CursoDTO> listarTodosCursos() {
        List<Curso> cursos = cursoRepository.findAll();
        return cursos.stream().map((Curso c) -> {
            return CursoDTO.builder()
                    .id(c.getId())
                    .nome(c.getNome())
                    .cargaHoraria(c.getCargaHoraria())
                    .categoriaCursoId(
                            c.getCategoriaCurso() != null ?
                                    c.getCategoriaCurso().getId() : 0
                    )
                    .categoriaCursoNome(
                            c.getCategoriaCurso() != null ?
                                    c.getCategoriaCurso().getNome() : ""
                    )
                    .build();
        }).collect(Collectors.toList());
    }

    @Override
    public CursoDTO buscaCursoPorId(Long id) {
        Curso curso = cursoRepository.findById(id)
                .orElseThrow( () -> new RegraNegocioException("Curso não encontrado"));
        return CursoDTO.builder()
                .id(curso.getId())
                .nome(curso.getNome())
                .cargaHoraria(curso.getCargaHoraria())
                .categoriaCursoId(
                        curso.getCategoriaCurso() != null ?
                                curso.getCategoriaCurso().getId() : 0)
                .categoriaCursoNome(
                        curso.getCategoriaCurso().getNome() != null ?
                                curso.getCategoriaCurso().getNome() : "")
                .build();
    }

    @Override
    public void excluiCurso(Long id) {
        cursoRepository.deleteById(id);
    }

    @Override
    @Transactional
    public CursoDTO atualizaCurso(Long id, AtualizaCursoReqDTO atualizaCursoReqDTO) {
        Curso curso = cursoRepository.findById(id)
                .orElseThrow( () -> new RegraNegocioException("Curso não encontrado"));
        if (atualizaCursoReqDTO.nome() != null) curso.setNome(atualizaCursoReqDTO.nome());
        if (atualizaCursoReqDTO.cargaHoraria() != null) curso.setCargaHoraria(atualizaCursoReqDTO.cargaHoraria());
        cursoRepository.save(curso);
        return CursoDTO.builder()
                .id(curso.getId())
                .nome(curso.getNome())
                .cargaHoraria(curso.getCargaHoraria())
                .categoriaCursoId(
                        curso.getCategoriaCurso() != null ? curso.getCategoriaCurso().getId() : 0
                )
                .categoriaCursoNome(
                        curso.getCategoriaCurso() != null ? curso.getCategoriaCurso().getNome() : ""
                )
                .build();
    }
}

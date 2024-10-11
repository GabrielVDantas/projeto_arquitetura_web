package com.example.projetoescola.dtos;

import lombok.Getter;

import java.util.Collections;
import java.util.List;

public class ApiErrorDTO {

    @Getter
    List<String> errors;

    public ApiErrorDTO(String mensagem) {
        this.errors = Collections.singletonList(mensagem);
    }
}

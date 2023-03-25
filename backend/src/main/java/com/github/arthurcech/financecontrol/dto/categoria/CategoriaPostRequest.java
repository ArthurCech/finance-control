package com.github.arthurcech.financecontrol.dto.categoria;

import com.fasterxml.jackson.annotation.JsonCreator;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CategoriaPostRequest {

    @NotBlank
    @Size(min = 1, max = 50)
    private final String nome;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public CategoriaPostRequest(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

}

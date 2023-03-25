package com.github.arthurcech.financecontrol.dto.categoria;

public class CategoriaResponse {

    private final Long codigo;
    private final String nome;

    public CategoriaResponse(Long codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
    }

    public Long getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

}

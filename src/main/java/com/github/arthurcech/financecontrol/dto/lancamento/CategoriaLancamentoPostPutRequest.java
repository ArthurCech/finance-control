package com.github.arthurcech.financecontrol.dto.lancamento;

import com.fasterxml.jackson.annotation.JsonCreator;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class CategoriaLancamentoPostPutRequest {

    @NotNull
    @Positive
    private final Long codigo;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public CategoriaLancamentoPostPutRequest(Long codigo) {
        this.codigo = codigo;
    }

    public Long getCodigo() {
        return codigo;
    }

}

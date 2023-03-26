package com.github.arthurcech.financecontrol.dto.lancamento;

import com.fasterxml.jackson.annotation.JsonCreator;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class PessoaLancamentoPostRequest {

    @NotNull
    @Positive
    private final Long codigo;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public PessoaLancamentoPostRequest(Long codigo) {
        this.codigo = codigo;
    }

    public Long getCodigo() {
        return codigo;
    }

}

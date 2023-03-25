package com.github.arthurcech.financecontrol.dto.pessoa;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PessoaPutRequest {

    @NotBlank
    @Size(min = 1, max = 50)
    private final String nome;
    @Valid
    private final EnderecoPostPutRequest endereco;
    @NotNull
    private final Boolean ativo;

    public PessoaPutRequest(
            String nome,
            EnderecoPostPutRequest endereco,
            Boolean ativo
    ) {
        this.nome = nome;
        this.endereco = endereco;
        this.ativo = ativo;
    }

    public String getNome() {
        return nome;
    }

    public EnderecoPostPutRequest getEndereco() {
        return endereco;
    }

    public Boolean getAtivo() {
        return ativo;
    }

}

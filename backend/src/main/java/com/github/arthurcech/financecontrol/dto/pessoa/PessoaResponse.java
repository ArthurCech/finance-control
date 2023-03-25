package com.github.arthurcech.financecontrol.dto.pessoa;

public class PessoaResponse {

    private final Long codigo;
    private final String nome;
    private final EnderecoResponse endereco;
    private final Boolean ativo;

    public PessoaResponse(
            Long codigo,
            String nome,
            EnderecoResponse endereco,
            Boolean ativo
    ) {
        this.codigo = codigo;
        this.nome = nome;
        this.endereco = endereco;
        this.ativo = ativo;
    }

    public Long getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public EnderecoResponse getEndereco() {
        return endereco;
    }

    public Boolean getAtivo() {
        return ativo;
    }

}

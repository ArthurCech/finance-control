package com.github.arthurcech.financecontrol.dto.pessoa;

import javax.validation.constraints.Size;

public class EnderecoPostPutRequest {

    @Size(min = 1, max = 30)
    private final String logradouro;
    @Size(min = 1, max = 30)
    private final String numero;
    @Size(min = 1, max = 30)
    private final String complemento;
    @Size(min = 1, max = 30)
    private final String bairro;
    @Size(min = 1, max = 30)
    private final String cep;
    @Size(min = 1, max = 30)
    private final String cidade;
    @Size(min = 1, max = 30)
    private final String estado;

    public EnderecoPostPutRequest(
            String logradouro,
            String numero,
            String complemento,
            String bairro,
            String cep,
            String cidade,
            String estado
    ) {
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cep = cep;
        this.cidade = cidade;
        this.estado = estado;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public String getCep() {
        return cep;
    }

    public String getCidade() {
        return cidade;
    }

    public String getEstado() {
        return estado;
    }

}

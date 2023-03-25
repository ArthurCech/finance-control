package com.github.arthurcech.financecontrol.dto.pessoa;

public class EnderecoResponse {

    private final String logradouro;
    private final String numero;
    private final String complemento;
    private final String bairro;
    private final String cep;
    private final String cidade;
    private final String estado;

    public EnderecoResponse(
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

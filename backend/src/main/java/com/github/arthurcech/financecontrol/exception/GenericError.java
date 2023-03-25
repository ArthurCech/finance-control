package com.github.arthurcech.financecontrol.exception;

public class GenericError {

    private final String mensagemUsuario;
    private final String mensagemDesenvolvedor;

    public GenericError(String mensagemUsuario, String mensagemDesenvolvedor) {
        this.mensagemUsuario = mensagemUsuario;
        this.mensagemDesenvolvedor = mensagemDesenvolvedor;
    }

    public String getMensagemUsuario() {
        return mensagemUsuario;
    }

    public String getMensagemDesenvolvedor() {
        return mensagemDesenvolvedor;
    }

}

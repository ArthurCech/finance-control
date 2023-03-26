package com.github.arthurcech.financecontrol.event;

import org.springframework.context.ApplicationEvent;

import javax.servlet.http.HttpServletResponse;

public class RecursoCriadoEvent extends ApplicationEvent {

    private static final long serialVersionUID = 1L;

    private final HttpServletResponse httpServletResponse;
    private final Long codigo;

    public RecursoCriadoEvent(Object source, HttpServletResponse httpServletResponse, Long codigo) {
        super(source);
        this.httpServletResponse = httpServletResponse;
        this.codigo = codigo;
    }

    public HttpServletResponse getHttpServletResponse() {
        return httpServletResponse;
    }

    public Long getCodigo() {
        return codigo;
    }

}

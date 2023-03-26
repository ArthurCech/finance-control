package com.github.arthurcech.financecontrol.repository.projection;

import com.github.arthurcech.financecontrol.domain.TipoLancamento;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ResumoLancamento {

    private final Long codigo;
    private final String descricao;
    private final LocalDate dataVencimento;
    private final LocalDate dataPagamento;
    private final BigDecimal valor;
    private final TipoLancamento tipo;
    private final String categoria;
    private final String pessoa;

    public ResumoLancamento(
            Long codigo,
            String descricao,
            LocalDate dataVencimento,
            LocalDate dataPagamento,
            BigDecimal valor,
            TipoLancamento tipo,
            String categoria,
            String pessoa
    ) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.dataVencimento = dataVencimento;
        this.dataPagamento = dataPagamento;
        this.valor = valor;
        this.tipo = tipo;
        this.categoria = categoria;
        this.pessoa = pessoa;
    }

    public Long getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public LocalDate getDataPagamento() {
        return dataPagamento;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public TipoLancamento getTipo() {
        return tipo;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getPessoa() {
        return pessoa;
    }

}

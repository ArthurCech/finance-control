package com.github.arthurcech.financecontrol.dto.lancamento;

import com.github.arthurcech.financecontrol.domain.TipoLancamento;
import com.github.arthurcech.financecontrol.dto.categoria.CategoriaResponse;
import com.github.arthurcech.financecontrol.dto.pessoa.PessoaResponse;

import java.math.BigDecimal;
import java.time.LocalDate;

public class LancamentoResponse {

    private final Long codigo;
    private final String descricao;
    private final LocalDate dataVencimento;
    private final LocalDate dataPagamento;
    private final BigDecimal valor;
    private final String observacao;
    private final TipoLancamento tipo;
    private final CategoriaResponse categoria;
    private final PessoaResponse pessoa;

    public LancamentoResponse(
            Long codigo,
            String descricao,
            LocalDate dataVencimento,
            LocalDate dataPagamento,
            BigDecimal valor,
            String observacao,
            TipoLancamento tipo,
            CategoriaResponse categoria,
            PessoaResponse pessoa
    ) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.dataVencimento = dataVencimento;
        this.dataPagamento = dataPagamento;
        this.valor = valor;
        this.observacao = observacao;
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

    public String getObservacao() {
        return observacao;
    }

    public TipoLancamento getTipo() {
        return tipo;
    }

    public CategoriaResponse getCategoria() {
        return categoria;
    }

    public PessoaResponse getPessoa() {
        return pessoa;
    }

}

package com.github.arthurcech.financecontrol.dto.lancamento;

import com.github.arthurcech.financecontrol.domain.TipoLancamento;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;

public class LancamentoPutRequest {

    @NotBlank
    @Size(min = 1, max = 50)
    private final String descricao;
    @NotNull
    private final LocalDate dataVencimento;
    private final LocalDate dataPagamento;
    @NotNull
    @Positive
    private final BigDecimal valor;
    @Size(min = 1, max = 100)
    private final String observacao;
    @NotNull
    private final TipoLancamento tipo;
    @NotNull
    @Valid
    private final CategoriaLancamentoPostPutRequest categoria;
    @NotNull
    @Valid
    private final PessoaLancamentoPostPutRequest pessoa;

    public LancamentoPutRequest(
            String descricao,
            LocalDate dataVencimento,
            LocalDate dataPagamento,
            BigDecimal valor,
            String observacao,
            TipoLancamento tipo,
            CategoriaLancamentoPostPutRequest categoria,
            PessoaLancamentoPostPutRequest pessoa
    ) {
        this.descricao = descricao;
        this.dataVencimento = dataVencimento;
        this.dataPagamento = dataPagamento;
        this.valor = valor;
        this.observacao = observacao;
        this.tipo = tipo;
        this.categoria = categoria;
        this.pessoa = pessoa;
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

    public CategoriaLancamentoPostPutRequest getCategoria() {
        return categoria;
    }

    public PessoaLancamentoPostPutRequest getPessoa() {
        return pessoa;
    }

}

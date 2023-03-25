package com.github.arthurcech.financecontrol.repository.lancamento;

import com.github.arthurcech.financecontrol.domain.Lancamento;
import com.github.arthurcech.financecontrol.repository.filter.LancamentoFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LancamentoRepositoryQuery {

    Page<Lancamento> filtrar(LancamentoFilter lancamentoFilter, Pageable pageable);

}

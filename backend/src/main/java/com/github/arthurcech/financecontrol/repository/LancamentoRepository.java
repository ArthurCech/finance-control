package com.github.arthurcech.financecontrol.repository;

import com.github.arthurcech.financecontrol.domain.Lancamento;
import com.github.arthurcech.financecontrol.repository.lancamento.LancamentoRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long>, LancamentoRepositoryQuery {
}

package com.github.arthurcech.financecontrol.repository.lancamento;

import com.github.arthurcech.financecontrol.domain.Categoria_;
import com.github.arthurcech.financecontrol.domain.Lancamento;
import com.github.arthurcech.financecontrol.domain.Lancamento_;
import com.github.arthurcech.financecontrol.domain.Pessoa_;
import com.github.arthurcech.financecontrol.repository.filter.LancamentoFilter;
import com.github.arthurcech.financecontrol.repository.projection.ResumoLancamento;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class LancamentoRepositoryImpl implements LancamentoRepositoryQuery {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Page<Lancamento> filtrar(LancamentoFilter filtros, Pageable pageable) {
        CriteriaBuilder cb = manager.getCriteriaBuilder();
        CriteriaQuery<Lancamento> cq = cb.createQuery(Lancamento.class);

        Root<Lancamento> lancamento = cq.from(Lancamento.class);
        Predicate[] predicates = criarRestricoes(filtros, cb, lancamento);
        cq.where(predicates);

        TypedQuery<Lancamento> query = manager.createQuery(cq);
        adicionarRestricoesDePaginacao(query, pageable);
        return new PageImpl<>(query.getResultList(), pageable, total(filtros));
    }

    @Override
    public Page<ResumoLancamento> resumir(LancamentoFilter filtros, Pageable pageable) {
        CriteriaBuilder cb = manager.getCriteriaBuilder();
        CriteriaQuery<ResumoLancamento> cq = cb.createQuery(ResumoLancamento.class);

        Root<Lancamento> lancamento = cq.from(Lancamento.class);
        cq.select(cb.construct(ResumoLancamento.class,
                lancamento.get(Lancamento_.codigo), lancamento.get(Lancamento_.descricao),
                lancamento.get(Lancamento_.dataVencimento), lancamento.get(Lancamento_.dataPagamento),
                lancamento.get(Lancamento_.valor), lancamento.get(Lancamento_.tipo),
                lancamento.get(Lancamento_.categoria).get(Categoria_.nome),
                lancamento.get(Lancamento_.pessoa).get(Pessoa_.nome)
        ));
        Predicate[] predicates = criarRestricoes(filtros, cb, lancamento);
        cq.where(predicates);

        TypedQuery<ResumoLancamento> query = manager.createQuery(cq);
        adicionarRestricoesDePaginacao(query, pageable);
        return new PageImpl<>(query.getResultList(), pageable, total(filtros));
    }

    private Predicate[] criarRestricoes(LancamentoFilter filtros, CriteriaBuilder cb, Root<Lancamento> lancamento) {
        List<Predicate> predicates = new ArrayList<>();

        if (temDescricao(filtros)) {
            predicates.add(
                    cb.like(
                            cb.lower(lancamento.get(Lancamento_.descricao)),
                            "%" + filtros.getDescricao().toLowerCase() + "%"
                    )
            );
        }

        if (temDataVencimentoDe(filtros)) {
            predicates.add(
                    cb.greaterThanOrEqualTo(
                            lancamento.get(Lancamento_.dataVencimento),
                            filtros.getDataVencimentoDe()
                    )
            );
        }

        if (temDataVencimentoAte(filtros)) {
            predicates.add(
                    cb.lessThanOrEqualTo(
                            lancamento.get(Lancamento_.dataVencimento),
                            filtros.getDataVencimentoAte()
                    )
            );
        }

        return predicates.toArray(new Predicate[0]);
    }

    private void adicionarRestricoesDePaginacao(TypedQuery<?> query, Pageable pageable) {
        int paginaAtual = pageable.getPageNumber();
        int totalRegistrosPorPagina = pageable.getPageSize();
        int primeiroRegistroDaPagina = paginaAtual * totalRegistrosPorPagina;
        query.setFirstResult(primeiroRegistroDaPagina);
        query.setMaxResults(totalRegistrosPorPagina);
    }

    private Long total(LancamentoFilter filtros) {
        CriteriaBuilder cb = manager.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);

        Root<Lancamento> root = cq.from(Lancamento.class);
        Predicate[] predicates = criarRestricoes(filtros, cb, root);
        cq.where(predicates);
        cq.select(cb.count(root));

        return manager.createQuery(cq).getSingleResult();
    }

    private boolean temDescricao(LancamentoFilter filtros) {
        return !StringUtils.isBlank(filtros.getDescricao());
    }

    private boolean temDataVencimentoDe(LancamentoFilter filtros) {
        return filtros.getDataVencimentoDe() != null;
    }

    private boolean temDataVencimentoAte(LancamentoFilter filtros) {
        return filtros.getDataVencimentoAte() != null;
    }

}

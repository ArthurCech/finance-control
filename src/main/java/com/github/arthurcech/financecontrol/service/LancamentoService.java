package com.github.arthurcech.financecontrol.service;

import com.github.arthurcech.financecontrol.domain.Categoria;
import com.github.arthurcech.financecontrol.domain.Lancamento;
import com.github.arthurcech.financecontrol.domain.Pessoa;
import com.github.arthurcech.financecontrol.dto.lancamento.LancamentoPostRequest;
import com.github.arthurcech.financecontrol.dto.mapper.LancamentoMapper;
import com.github.arthurcech.financecontrol.repository.CategoriaRepository;
import com.github.arthurcech.financecontrol.repository.LancamentoRepository;
import com.github.arthurcech.financecontrol.repository.PessoaRepository;
import com.github.arthurcech.financecontrol.service.exception.CategoriaInexistenteException;
import com.github.arthurcech.financecontrol.service.exception.PessoaInexistenteOuInativaException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LancamentoService {

    private final LancamentoRepository lancamentoRepository;
    private final PessoaRepository pessoaRepository;
    private final CategoriaRepository categoriaRepository;

    public LancamentoService(
            PessoaRepository pessoaRepository,
            LancamentoRepository lancamentoRepository,
            CategoriaRepository categoriaRepository
    ) {
        this.pessoaRepository = pessoaRepository;
        this.lancamentoRepository = lancamentoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    public Lancamento salvar(LancamentoPostRequest lancamentoPostRequest) {
        Optional<Pessoa> pessoa = pessoaRepository.findById(lancamentoPostRequest.getPessoa().getCodigo());
        if (pessoa.isEmpty() || pessoa.get().isInativo()) {
            throw new PessoaInexistenteOuInativaException();
        }

        Optional<Categoria> categoria = categoriaRepository.findById(lancamentoPostRequest.getCategoria().getCodigo());
        if (categoria.isEmpty()) {
            throw new CategoriaInexistenteException();
        }

        Lancamento lancamento = LancamentoMapper.INSTANCE.toLancamento(lancamentoPostRequest);
        return lancamentoRepository.save(lancamento);
    }

}

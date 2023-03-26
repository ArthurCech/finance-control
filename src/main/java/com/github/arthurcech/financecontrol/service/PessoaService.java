package com.github.arthurcech.financecontrol.service;

import com.github.arthurcech.financecontrol.domain.Pessoa;
import com.github.arthurcech.financecontrol.dto.mapper.PessoaMapper;
import com.github.arthurcech.financecontrol.dto.pessoa.PessoaPutRequest;
import com.github.arthurcech.financecontrol.repository.PessoaRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class PessoaService {

    private final PessoaRepository pessoaRepository;

    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    public Pessoa atualizar(Long codigo, PessoaPutRequest pessoaPutRequest) {
        Pessoa pessoa = buscarPessoaPeloCodigo(codigo);
        PessoaMapper.INSTANCE.updatePessoaFromDto(pessoaPutRequest, pessoa);
        return pessoaRepository.save(pessoa);
    }

    public void atualizarPropriedadeAtivo(Long codigo, Boolean ativo) {
        Pessoa pessoaSalva = buscarPessoaPeloCodigo(codigo);
        pessoaSalva.setAtivo(ativo);
        pessoaRepository.save(pessoaSalva);
    }

    private Pessoa buscarPessoaPeloCodigo(Long codigo) {
        return pessoaRepository.findById(codigo)
                .orElseThrow(() -> new EmptyResultDataAccessException(1));
    }

}

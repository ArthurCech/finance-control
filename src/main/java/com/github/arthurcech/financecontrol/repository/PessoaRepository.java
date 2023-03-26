package com.github.arthurcech.financecontrol.repository;

import com.github.arthurcech.financecontrol.domain.Pessoa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    Page<Pessoa> findByNomeContaining(String nome, Pageable pageable);

}

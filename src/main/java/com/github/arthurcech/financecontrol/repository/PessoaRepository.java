package com.github.arthurcech.financecontrol.repository;

import com.github.arthurcech.financecontrol.domain.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
}

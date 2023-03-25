package com.github.arthurcech.financecontrol.repository;

import com.github.arthurcech.financecontrol.domain.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}

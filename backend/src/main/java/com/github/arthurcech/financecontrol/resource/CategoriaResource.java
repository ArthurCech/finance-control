package com.github.arthurcech.financecontrol.resource;

import com.github.arthurcech.financecontrol.domain.Categoria;
import com.github.arthurcech.financecontrol.dto.categoria.CategoriaPostRequest;
import com.github.arthurcech.financecontrol.dto.categoria.CategoriaResponse;
import com.github.arthurcech.financecontrol.dto.mapper.CategoriaMapper;
import com.github.arthurcech.financecontrol.event.RecursoCriadoEvent;
import com.github.arthurcech.financecontrol.repository.CategoriaRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaResource {

    private final CategoriaRepository categoriaRepository;
    private final ApplicationEventPublisher eventPublisher;

    public CategoriaResource(
            CategoriaRepository categoriaRepository,
            ApplicationEventPublisher eventPublisher
    ) {
        this.categoriaRepository = categoriaRepository;
        this.eventPublisher = eventPublisher;
    }

    @GetMapping
    public List<CategoriaResponse> listar() {
        return categoriaRepository.findAll().stream()
                .map(CategoriaMapper.INSTANCE::toCategoriaResponse)
                .collect(Collectors.toList());
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<CategoriaResponse> buscarPeloCodigo(@PathVariable Long codigo) {
        return categoriaRepository.findById(codigo)
                .map(categoria -> ResponseEntity.ok(CategoriaMapper.INSTANCE.toCategoriaResponse(categoria)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoriaResponse criar(@Valid @RequestBody CategoriaPostRequest categoriaPostRequest,
                                   HttpServletResponse response) {
        Categoria categoria = CategoriaMapper.INSTANCE.toCategoria(categoriaPostRequest);
        categoriaRepository.save(categoria);
        eventPublisher.publishEvent(new RecursoCriadoEvent(this, response, categoria.getCodigo()));
        return CategoriaMapper.INSTANCE.toCategoriaResponse(categoria);
    }

}

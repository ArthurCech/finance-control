package com.github.arthurcech.financecontrol.resource;

import com.github.arthurcech.financecontrol.domain.Lancamento;
import com.github.arthurcech.financecontrol.dto.lancamento.LancamentoPostRequest;
import com.github.arthurcech.financecontrol.dto.lancamento.LancamentoResponse;
import com.github.arthurcech.financecontrol.dto.mapper.LancamentoMapper;
import com.github.arthurcech.financecontrol.event.RecursoCriadoEvent;
import com.github.arthurcech.financecontrol.repository.LancamentoRepository;
import com.github.arthurcech.financecontrol.repository.filter.LancamentoFilter;
import com.github.arthurcech.financecontrol.service.LancamentoService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/lancamentos")
public class LancamentoResource {

    private final LancamentoRepository lancamentoRepository;
    private final LancamentoService lancamentoService;
    private final ApplicationEventPublisher eventPublisher;

    public LancamentoResource(
            LancamentoRepository lancamentoRepository,
            LancamentoService lancamentoService,
            ApplicationEventPublisher eventPublisher
    ) {
        this.lancamentoRepository = lancamentoRepository;
        this.lancamentoService = lancamentoService;
        this.eventPublisher = eventPublisher;
    }

    @GetMapping
    public Page<LancamentoResponse> pesquisar(LancamentoFilter lancamentoFilter, Pageable pageable) {
        return lancamentoRepository.filtrar(lancamentoFilter, pageable)
                .map(LancamentoMapper.INSTANCE::toLancamentoResponse);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<LancamentoResponse> buscarPeloCodigo(@PathVariable Long codigo) {
        return lancamentoRepository.findById(codigo)
                .map(lancamento -> ResponseEntity.ok(LancamentoMapper.INSTANCE.toLancamentoResponse(lancamento)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LancamentoResponse criar(@Valid @RequestBody LancamentoPostRequest lancamentoPostRequest,
                                    HttpServletResponse response) {
        Lancamento lancamento = lancamentoService.salvar(lancamentoPostRequest);
        eventPublisher.publishEvent(new RecursoCriadoEvent(this, response, lancamento.getCodigo()));
        return LancamentoMapper.INSTANCE.toLancamentoResponse(lancamento);
    }

    @DeleteMapping("/{codigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long codigo) {
        lancamentoRepository.deleteById(codigo);
    }

}

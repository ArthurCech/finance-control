package com.github.arthurcech.financecontrol.resource;

import com.github.arthurcech.financecontrol.domain.Pessoa;
import com.github.arthurcech.financecontrol.dto.mapper.PessoaMapper;
import com.github.arthurcech.financecontrol.dto.pessoa.PessoaPostRequest;
import com.github.arthurcech.financecontrol.dto.pessoa.PessoaPutRequest;
import com.github.arthurcech.financecontrol.dto.pessoa.PessoaResponse;
import com.github.arthurcech.financecontrol.event.RecursoCriadoEvent;
import com.github.arthurcech.financecontrol.repository.PessoaRepository;
import com.github.arthurcech.financecontrol.service.PessoaService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/pessoas")
public class PessoaResource {

    private final PessoaRepository pessoaRepository;
    private final PessoaService pessoaService;
    private final ApplicationEventPublisher eventPublisher;

    public PessoaResource(
            PessoaRepository pessoaRepository,
            PessoaService pessoaService,
            ApplicationEventPublisher eventPublisher
    ) {
        this.pessoaRepository = pessoaRepository;
        this.pessoaService = pessoaService;
        this.eventPublisher = eventPublisher;
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<PessoaResponse> buscarPeloCodigo(@PathVariable Long codigo) {
        return pessoaRepository.findById(codigo)
                .map(pessoa -> ResponseEntity.ok(PessoaMapper.INSTANCE.toPessoaResponse(pessoa)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PessoaResponse criar(@Valid @RequestBody PessoaPostRequest pessoaPostRequest,
                                HttpServletResponse response) {
        Pessoa pessoa = PessoaMapper.INSTANCE.toPessoa(pessoaPostRequest);
        pessoaRepository.save(pessoa);
        eventPublisher.publishEvent(new RecursoCriadoEvent(this, response, pessoa.getCodigo()));
        return PessoaMapper.INSTANCE.toPessoaResponse(pessoa);
    }

    @DeleteMapping("/{codigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long codigo) {
        pessoaRepository.deleteById(codigo);
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<PessoaResponse> atualizar(@PathVariable Long codigo,
                                                    @Valid @RequestBody PessoaPutRequest pessoaPutRequest) {
        Pessoa pessoa = pessoaService.atualizar(codigo, pessoaPutRequest);
        return ResponseEntity.ok(PessoaMapper.INSTANCE.toPessoaResponse(pessoa));
    }

    @PutMapping("/{codigo}/ativo")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizarPropriedadeAtivo(@PathVariable Long codigo, @RequestBody Boolean ativo) {
        pessoaService.atualizarPropriedadeAtivo(codigo, ativo);
    }

}

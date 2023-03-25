package com.github.arthurcech.financecontrol.handler;

import com.github.arthurcech.financecontrol.exception.GenericError;
import com.github.arthurcech.financecontrol.service.exception.CategoriaInexistenteException;
import com.github.arthurcech.financecontrol.service.exception.PessoaInexistenteOuInativaException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    private final MessageSource msgSource;

    public ApiExceptionHandler(MessageSource msgSource) {
        this.msgSource = msgSource;
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request
    ) {
        String msgUsuario = msgSource.getMessage("mensagem.invalida", null, LocaleContextHolder.getLocale());
        String msgDesenvolvedor = ex.getCause() != null ? ex.getCause().toString() : ex.toString();
        GenericError genericError = new GenericError(msgUsuario, msgDesenvolvedor);
        return handleExceptionInternal(ex, List.of(genericError), headers, HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request
    ) {
        List<GenericError> genericErrors = criarListaDeErros(ex.getBindingResult());
        return handleExceptionInternal(ex, genericErrors, headers, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({EmptyResultDataAccessException.class})
    public ResponseEntity<Object> handleEmptyResultDataAccessException(
            EmptyResultDataAccessException ex,
            WebRequest request
    ) {
        String msgUsuario = msgSource.getMessage("recurso.nao-encontrado", null, LocaleContextHolder.getLocale());
        String msgDesenvolvedor = ex.toString();
        GenericError genericError = new GenericError(msgUsuario, msgDesenvolvedor);
        return handleExceptionInternal(ex, List.of(genericError), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler({DataIntegrityViolationException.class})
    public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex, WebRequest request) {
        String msgUsuario = msgSource.getMessage("recurso.operacao-nao-permitida", null, LocaleContextHolder.getLocale());
        String msgDesenvolvedor = ExceptionUtils.getRootCauseMessage(ex);
        GenericError genericError = new GenericError(msgUsuario, msgDesenvolvedor);
        return handleExceptionInternal(ex, List.of(genericError), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({PessoaInexistenteOuInativaException.class})
    public ResponseEntity<Object> handlePessoaInexistenteOuInativaException(PessoaInexistenteOuInativaException ex) {
        String msgUsuario = msgSource.getMessage("pessoa.inexistente-ou-inativa", null, LocaleContextHolder.getLocale());
        String msgDesenvolvedor = ex.toString();
        return ResponseEntity.badRequest().body(List.of(new GenericError(msgUsuario, msgDesenvolvedor)));
    }

    @ExceptionHandler({CategoriaInexistenteException.class})
    public ResponseEntity<Object> handleCategoriaInexistenteException(CategoriaInexistenteException ex) {
        String msgUsuario = msgSource.getMessage("categoria.inexistente", null, LocaleContextHolder.getLocale());
        String msgDesenvolvedor = ex.toString();
        return ResponseEntity.badRequest().body(List.of(new GenericError(msgUsuario, msgDesenvolvedor)));
    }

    private List<GenericError> criarListaDeErros(BindingResult bindingResult) {
        return bindingResult.getFieldErrors().stream()
                .map(fieldError -> {
                    String msgUsuario = msgSource.getMessage(fieldError, LocaleContextHolder.getLocale());
                    String msgDesenvolvedor = fieldError.toString();
                    return new GenericError(msgUsuario, msgDesenvolvedor);
                }).collect(Collectors.toList());
    }

}

package com.github.arthurcech.financecontrol.dto.mapper;

import com.github.arthurcech.financecontrol.domain.Lancamento;
import com.github.arthurcech.financecontrol.dto.lancamento.LancamentoPostRequest;
import com.github.arthurcech.financecontrol.dto.lancamento.LancamentoPutRequest;
import com.github.arthurcech.financecontrol.dto.lancamento.LancamentoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface LancamentoMapper {

    LancamentoMapper INSTANCE = Mappers.getMapper(LancamentoMapper.class);

    LancamentoResponse toLancamentoResponse(Lancamento lancamento);

    Lancamento toLancamento(LancamentoPostRequest lancamentoPostRequest);

    @Mapping(target = "categoria", ignore = true)
    @Mapping(target = "pessoa", ignore = true)
    void updateLancamentoFromDto(LancamentoPutRequest lancamentoPutRequest, @MappingTarget Lancamento lancamento);

}

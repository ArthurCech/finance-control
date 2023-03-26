package com.github.arthurcech.financecontrol.dto.mapper;

import com.github.arthurcech.financecontrol.domain.Lancamento;
import com.github.arthurcech.financecontrol.dto.lancamento.LancamentoPostRequest;
import com.github.arthurcech.financecontrol.dto.lancamento.LancamentoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface LancamentoMapper {

    LancamentoMapper INSTANCE = Mappers.getMapper(LancamentoMapper.class);

    LancamentoResponse toLancamentoResponse(Lancamento lancamento);

    Lancamento toLancamento(LancamentoPostRequest lancamentoPostRequest);

}

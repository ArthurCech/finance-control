package com.github.arthurcech.financecontrol.dto.mapper;

import com.github.arthurcech.financecontrol.domain.Pessoa;
import com.github.arthurcech.financecontrol.dto.pessoa.PessoaPostRequest;
import com.github.arthurcech.financecontrol.dto.pessoa.PessoaPutRequest;
import com.github.arthurcech.financecontrol.dto.pessoa.PessoaResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PessoaMapper {

    PessoaMapper INSTANCE = Mappers.getMapper(PessoaMapper.class);

    PessoaResponse toPessoaResponse(Pessoa pessoa);

    Pessoa toPessoa(PessoaPostRequest pessoaPostRequest);

    void updatePessoaFromDto(PessoaPutRequest pessoaPutRequest, @MappingTarget Pessoa pessoa);

}

package com.github.arthurcech.financecontrol.dto.mapper;

import com.github.arthurcech.financecontrol.domain.Categoria;
import com.github.arthurcech.financecontrol.dto.categoria.CategoriaPostRequest;
import com.github.arthurcech.financecontrol.dto.categoria.CategoriaResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CategoriaMapper {

    CategoriaMapper INSTANCE = Mappers.getMapper(CategoriaMapper.class);

    CategoriaResponse toCategoriaResponse(Categoria categoria);

    Categoria toCategoria(CategoriaPostRequest categoriaPostRequest);

}

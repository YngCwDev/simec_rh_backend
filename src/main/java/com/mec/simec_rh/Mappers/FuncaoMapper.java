package com.mec.simec_rh.Mappers;

import com.mec.simec_rh.DTOs.AlocacaoDTO;
import com.mec.simec_rh.Entities.Alocacao;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface FuncaoMapper {

    FuncaoMapper INSTANCE = Mappers.getMapper(FuncaoMapper.class);

    AlocacaoDTO toDto(Alocacao alocacao);

    Alocacao toEntity(AlocacaoDTO dto);
}

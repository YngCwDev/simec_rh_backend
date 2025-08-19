package com.mec.simec_rh.Mappers;

import com.mec.simec_rh.DTOs.SituacaoQuadroDTO;
import com.mec.simec_rh.Entities.SituacaoQuadro;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SituacaoQuadroMapper {

    SituacaoQuadroMapper INSTANCE = Mappers.getMapper(SituacaoQuadroMapper.class);

    SituacaoQuadroDTO toDto(SituacaoQuadro situacaoQuadro);

    SituacaoQuadro toEntity(SituacaoQuadroDTO dto);
}

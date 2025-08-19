package com.mec.simec_rh.Mappers;

import com.mec.simec_rh.DTOs.UnidadeOrganicaDTO;
import com.mec.simec_rh.Entities.UnidadeOrganica;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UnidadeOrganicaMapper {

    UnidadeOrganicaMapper INSTANCE = Mappers.getMapper(UnidadeOrganicaMapper.class);

    UnidadeOrganicaDTO toDto(UnidadeOrganica unidadeOrganica);

    UnidadeOrganica toEntity(UnidadeOrganicaDTO dto);
}

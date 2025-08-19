package com.mec.simec_rh.Mappers;

import com.mec.simec_rh.DTOs.HistoricoCarreiraDTO;
import com.mec.simec_rh.Entities.HistoricoCarreira;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface HistoricoCarreiraMapper {

    HistoricoCarreiraMapper INSTANCE = Mappers.getMapper(HistoricoCarreiraMapper.class);

    HistoricoCarreiraDTO toDto(HistoricoCarreira historicoCarreira);

    HistoricoCarreira toEntity(HistoricoCarreiraDTO dto);
}

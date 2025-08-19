package com.mec.simec_rh.Mappers;

import com.mec.simec_rh.DTOs.Funcionario.FuncionarioDTO;
import com.mec.simec_rh.Entities.Funcionario;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface FormacaoMapper {

    FormacaoMapper INSTANCE = Mappers.getMapper(FormacaoMapper.class);

    FuncionarioDTO toDto(Funcionario funcionario);

    Funcionario toEntity(FuncionarioDTO dto);
}

package com.mec.simec_rh.Mappers;

import com.mec.simec_rh.Entities.temp.AlocacaoMapper;
import com.mec.simec_rh.Entities.temp.FormacaoMapper;
import com.mec.simec_rh.Entities.temp.HistoricoCarreiraMapper;
import com.mec.simec_rh.Entities.temp.SituacaoQuadroMapper;
import com.mec.simec_rh.DTOs.Funcionario.FuncionarioDTO;
import com.mec.simec_rh.DTOs.Funcionario.FuncionarioFullDTO;
import com.mec.simec_rh.Entities.Funcionario;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {FormacaoMapper.class, HistoricoCarreiraMapper.class, AlocacaoMapper.class, SituacaoQuadroMapper.class})
public interface FuncionarioMapper {

    FuncionarioMapper INSTANCE = Mappers.getMapper(FuncionarioMapper.class);


    @Mapping(target = "provinciaNascimentoId", source = "provinciaNascimento.id")
    @Mapping(target = "formacoes", source = "formacaoList")
    @Mapping(target = "historicos", source = "historicoCarreiraList")
    @Mapping(target = "alocacoes", source = "alocacaoList")
    @Mapping(target = "situacoesQuadro", source = "situacaoQuadroList")
    FuncionarioFullDTO toFullDto(Funcionario funcionario);

    FuncionarioDTO toDto(Funcionario funcionario);

    Funcionario toEntity(FuncionarioDTO dto);

    @Mapping(target = "provinciaNascimento", ignore = true)
    @Mapping(target = "formacaoList", ignore = true)
    @Mapping(target = "historicoCarreiraList", ignore = true)
    @Mapping(target = "alocacaoList", ignore = true)
    @Mapping(target = "situacaoQuadroList", ignore = true)
    Funcionario toEntity(FuncionarioFullDTO d);

}

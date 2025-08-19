package com.mec.simec_rh.DTOs.Funcionario;

import com.mec.simec_rh.Entities.temp.AlocacaoDTO;
import com.mec.simec_rh.Entities.temp.FormacaoDTO;
import com.mec.simec_rh.Entities.temp.HistoricoCarreiraDTO;
import com.mec.simec_rh.Entities.temp.SituacaoQuadroDTO;
import com.mec.simec_rh.DTOs.ProvinciaDTO;

import java.util.List;

public record FuncionarioFullDTO(
        Long id,
        String nuit,
        String numeroCracha,
        String nomeCompleto,
        String numeroBi,
        String sexo,
        String estadoCivil,
        String nacionalidade,
        String dataNascimento,
        Integer idade,
        String contactoPrincipal,
        String contactoSecundario,
        String email,
        String dataIngressoAE,
        ProvinciaDTO provinciaNascimento,
        List<FormacaoDTO> formacoes,
        List<HistoricoCarreiraDTO> historicos,
        List<AlocacaoDTO> alocacoes,
        List<SituacaoQuadroDTO> situacoesQuadro,
        Boolean ativo
) {}
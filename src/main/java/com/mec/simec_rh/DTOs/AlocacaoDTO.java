package com.mec.simec_rh.DTOs;

import com.mec.simec_rh.DTOs.Funcionario.FuncionarioDTO;
import com.mec.simec_rh.Enums.Classe;
import com.mec.simec_rh.Enums.StatusAlocacao;

import java.time.LocalDate;
public record AlocacaoDTO(
        Long id,
        Classe classe,
        int escalao,
        LocalDate dataIncioAlocacao,
        LocalDate dataFinalAlocacao,
        StatusAlocacao statusAlocacao,
        String observacoes,
        Long funcionarioId,
        Long unidadeOrganicaId,
        Long provinciaId,
        Long distritoId,
        Long carreiraId,
        Long funcaoId,
        Long categoriaId,
        Long alocadoPorId,
        Boolean ativo
) {}

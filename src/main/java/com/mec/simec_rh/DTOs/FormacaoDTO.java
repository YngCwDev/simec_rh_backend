package com.mec.simec_rh.DTOs;

public record FormacaoDTO(
        Long id,
        String nivelEducacional,
        String dataConclusao,
        String especialidade,
        String instituicaoEnsino,
        String anoConclusao,
        String formacaoGeral,
        String formacaoTecnica,
        String formacaoEducacional,
        String formacaoMaisElevada,
        Long funcionarioId,
        Boolean ativo
) {}
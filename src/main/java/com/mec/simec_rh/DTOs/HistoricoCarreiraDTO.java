package com.mec.simec_rh.DTOs;

import java.time.LocalDate;

public record HistoricoCarreiraDTO(
        Long id,
        String accao,
        String classe,
        String escalao,
        String numeroProcessoVTAFuncao,
        LocalDate dataVTAClasse,
        LocalDate dataVTACarreira,
        LocalDate dataAnotacaoEscalao,
        LocalDate dataDespachoFuncao,
        LocalDate despachoCessacaoFuncao,
        Long funcionarioId,
        Boolean ativo
) {}
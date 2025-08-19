package com.mec.simec_rh.DTOs;

import java.time.LocalDate;

public record SituacaoQuadroDTO(
        Long id,
        LocalDate dataDespachoSituacao,
        String regimeEspecialActividade,
        LocalDate dataLicenca,
        LocalDate dataAnotacaoLicenca,
        String numeroProcessoLicenca,
        LocalDate dataDespachoCessacao,
        LocalDate dataAnotacaoCessacao,
        String observacoes,
        Long funcionarioId,
        Boolean ativo
) {}
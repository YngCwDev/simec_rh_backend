package com.mec.simec_rh.DTOs;

import com.mec.simec_rh.Enums.TipoUnidadeOrganica;

public record UnidadeOrganicaDTO(
        Long id,
        String nome,
        TipoUnidadeOrganica tipo
) {}
package com.mec.simec_rh.DTOs;

public record DistritoDTO(
        Long id,
        String nome,
        ProvinciaDTO provincia,
        Boolean ativo
) {
}
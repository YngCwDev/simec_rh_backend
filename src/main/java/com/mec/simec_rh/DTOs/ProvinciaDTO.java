package com.mec.simec_rh.DTOs;


public record ProvinciaDTO(
        Long id,
        String nome,
        String sigla,
        String regiao,
        Boolean ativa
) {}
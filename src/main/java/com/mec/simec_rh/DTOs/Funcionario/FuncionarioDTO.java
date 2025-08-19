package com.mec.simec_rh.DTOs.Funcionario;

import com.mec.simec_rh.DTOs.ProvinciaDTO;

public record FuncionarioDTO(
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
        Boolean ativo
) {}
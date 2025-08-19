package com.mec.simec_rh.DTOs;

import com.mec.simec_rh.Enums.Perfil;

public record UsuarioDTO(
        Long id,
        String username,
        String email,
        Perfil perfil
) {}
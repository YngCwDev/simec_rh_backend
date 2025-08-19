package com.mec.simec_rh.Mappers;

import com.mec.simec_rh.DTOs.UsuarioDTO;
import com.mec.simec_rh.Entities.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    UsuarioMapper INSTANCE = Mappers.getMapper(UsuarioMapper.class);

    UsuarioDTO toDto(Usuario usuario);

    Usuario toEntity(UsuarioDTO dto);
}

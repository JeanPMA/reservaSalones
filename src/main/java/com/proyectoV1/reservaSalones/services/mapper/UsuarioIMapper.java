package com.proyectoV1.reservaSalones.services.mapper;

import com.proyectoV1.reservaSalones.domain.entities.Usuario;
import com.proyectoV1.reservaSalones.dto.UsuarioDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper
public interface UsuarioIMapper {
   /* UsuarioIMapper INSTANCE = Mappers.getMapper(UsuarioIMapper.class);
    @Mapping(source = "rol", target = "rolDTO")
    UsuarioDTO toDTO(Usuario usuario);

    @Mapping(source = "rolDTO", target = "rol")
    Usuario toEntity(UsuarioDTO usuarioDTO);*/
}

package com.proyectoV1.reservaSalones.services.mapper;

import com.proyectoV1.reservaSalones.domain.entities.Rol;
import com.proyectoV1.reservaSalones.domain.entities.Servicio;
import com.proyectoV1.reservaSalones.dto.RolDTO;
import com.proyectoV1.reservaSalones.dto.ServicioDTO;
import org.springframework.stereotype.Component;

@Component
public class RolMapper  implements CustomMapper<RolDTO, Rol>{
    @Override
    public RolDTO toDto(Rol rol) {
        RolDTO rolDTO = new RolDTO();
        rolDTO.setId(rol.getId());
        rolDTO.setNombre(rol.getNombre());
        rolDTO.setCreated_at(rol.getCreated_at());

        return rolDTO;
    }
    @Override
    public Rol toEntity(RolDTO rolDTO) {
        Rol rol = new Rol();
        rol.setId(rolDTO.getId());
        rol.setNombre(rolDTO.getNombre());
        rol.setCreated_at(rolDTO.getCreated_at());

        return rol;
    }
}

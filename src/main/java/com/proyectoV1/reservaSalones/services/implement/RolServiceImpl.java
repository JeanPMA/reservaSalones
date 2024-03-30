package com.proyectoV1.reservaSalones.services.implement;

import com.proyectoV1.reservaSalones.domain.entities.Rol;
import com.proyectoV1.reservaSalones.dto.RolDTO;
import com.proyectoV1.reservaSalones.repositories.RolRepository;
import com.proyectoV1.reservaSalones.services.RolService;
import com.proyectoV1.reservaSalones.services.mapper.RolMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class RolServiceImpl implements RolService {
    private final RolRepository rolRepository;
    private final RolMapper rolMapper;

    public RolServiceImpl(RolRepository rolRepository, RolMapper rolMapper) {
        this.rolRepository = rolRepository;
        this.rolMapper = rolMapper;
    }
    @Override
    @Transactional(readOnly = true)
    public List<RolDTO> listarRol() {
        return rolRepository.findAll()
                .stream()
                .map(rolMapper::toDto).collect(Collectors.toList());
    }
    @Override
    public RolDTO save(RolDTO dto) {
        Rol rol = rolMapper.toEntity(dto);
        if (rol.getCreated_at() == null) {
            rol.setCreated_at(LocalDateTime.now());
        }
        rol = rolRepository.save(rol);
        return rolMapper.toDto(rol);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<RolDTO> getRolById(Integer rolId) {
        return rolRepository.findById(rolId).map(rolMapper::toDto);
    }

    @Override
    public void delete(Integer rolId) {
        rolRepository.deleteById(rolId);
    }
}

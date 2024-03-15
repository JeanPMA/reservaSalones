package com.proyectoV1.reservaSalones.services.implement;

import com.proyectoV1.reservaSalones.domain.entities.Usuario;
import com.proyectoV1.reservaSalones.dto.UsuarioDTO;
import com.proyectoV1.reservaSalones.repositories.UsuarioRepository;
import com.proyectoV1.reservaSalones.services.UsuarioService;
import com.proyectoV1.reservaSalones.services.mapper.UsuarioMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class UsuarioServiceImpl implements UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
    }
    @Override
    @Transactional(readOnly = true)
    public List<UsuarioDTO> listarUsuario() {
        return usuarioRepository.findAll()
                .stream()
                .map(usuarioMapper::toDto).collect(Collectors.toList());
    }
    @Override
    public UsuarioDTO save(UsuarioDTO dto) {
        Usuario usuario = usuarioMapper.toEntity(dto);
        usuario = usuarioRepository.save(usuario);
        return usuarioMapper.toDto(usuario);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UsuarioDTO> getUsuarioById(Long usuarioId) {
        return usuarioRepository.findById(usuarioId).map(usuarioMapper::toDto);
    }

    @Override
    public void delete(Long usuarioId) {
        usuarioRepository.deleteById(usuarioId);
    }
    @Override
    public Usuario parcial(UsuarioDTO dto, Long usuarioId){
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("UsuarioRol no encontrado " + usuarioId));

        if (dto.getEstado() != null) {
            usuario.setEstado(dto.getEstado());
        }
        return usuarioRepository.save(usuario);
    }
    @Override
    public Optional<UsuarioDTO> getUsuarioByUsername(String username){
        return usuarioRepository.findOneByUsername(username).map(usuarioMapper::toDto);
    }
}

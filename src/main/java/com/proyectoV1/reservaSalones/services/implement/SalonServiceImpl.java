package com.proyectoV1.reservaSalones.services.implement;

import com.proyectoV1.reservaSalones.domain.entities.Salon;

import com.proyectoV1.reservaSalones.domain.entities.Usuario;
import com.proyectoV1.reservaSalones.dto.SalonAvgDTO;
import com.proyectoV1.reservaSalones.dto.SalonDTO;
import com.proyectoV1.reservaSalones.repositories.SalonRepository;
import com.proyectoV1.reservaSalones.repositories.UsuarioRepository;
import com.proyectoV1.reservaSalones.services.SalonService;
import com.proyectoV1.reservaSalones.services.mapper.SalonMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class SalonServiceImpl implements SalonService {
    private final SalonRepository salonRepository;
    private final SalonMapper salonMapper;
    private final UsuarioRepository usuarioRepository;

    public SalonServiceImpl(SalonRepository salonRepository, SalonMapper salonMapper, UsuarioRepository usuarioRepository) {
        this.salonRepository = salonRepository;
        this.salonMapper = salonMapper;
        this.usuarioRepository = usuarioRepository;
    }
    @Override
    @Transactional(readOnly = true)
    public List<SalonDTO> listarSalon() {
        return salonRepository.findAllByOrderByNombre()
                .stream()
                .map(salonMapper::toDto).collect(Collectors.toList());
    }
    @Override
    @Transactional(readOnly = true)
    public List<SalonDTO> listarSalonPorCalificacion(){
        return salonRepository.listaSalonesByCalificacion();
    }
    @Override
    @Transactional(readOnly = true)
    public List<SalonDTO> listarSalonParaUsuarios(){
        List<SalonDTO> salones = salonRepository.findAllByEstadoOrderByNombre();
        return salones;
    }
    @Override
    @Transactional(readOnly = true)
    public List<SalonAvgDTO> listarSalonPorCalificacionAuth(){
        List<Object[]> results = salonRepository.listaSalonesByCalificacionForUserAuth();

        return results.stream()
                .map(result -> {
                    Salon salon = (Salon) result[0];
                    Double avgPuntuacion = (Double) result[1];

                    SalonAvgDTO dto = new SalonAvgDTO();
                    dto.setId(salon.getId());
                    dto.setNombre(salon.getNombre());
                    dto.setDireccion(salon.getDireccion());
                    dto.setCapacidad(salon.getCapacidad());
                    dto.setDescripcion(salon.getDescripcion());
                    dto.setBanner_id(salon.getBanner_id());
                    dto.setBanner_url(salon.getBanner_url());
                    dto.setTarifa(salon.getTarifa());
                    dto.setEstado(salon.getEstado());
                    dto.setUsuario(salon.getUsuario());
                    dto.setCreated_at(salon.getCreated_at());
                    dto.setServicios(salon.getServicios());
                    dto.setAvgPuntuacion(avgPuntuacion);
                    return dto;
                })
                .collect(Collectors.toList());
    }
    @Override
    @Transactional(readOnly = true)
    public List<SalonDTO> listarSalonParaUsuariosAuth(){
        List<Salon> salones = salonRepository.findAllSalonForUserAuth();
        return salones.stream()
                .map(salonMapper::toDto).collect(Collectors.toList());
    }
    @Override
    public SalonDTO save(SalonDTO dto, String username) {
        Salon salon = salonMapper.toEntity(dto);
        if (salon.getCreated_at() == null) {
            salon.setCreated_at(LocalDateTime.now());
        }
        Optional<Usuario> optionalUsuario = usuarioRepository.findOneByUsername(username);

        if (optionalUsuario.isPresent()) {
            salon.setUsuario(optionalUsuario.get());
        } else {
            throw new RuntimeException("Usuario no encontrado: " + username);
        }
        salon = salonRepository.save(salon);
        return salonMapper.toDto(salon);
    }
    @Override
    public SalonDTO saveForAdmin(SalonDTO dto) {
        Salon salon = salonMapper.toEntity(dto);
        if (salon.getCreated_at() == null) {
            salon.setCreated_at(LocalDateTime.now());
        }
        salon = salonRepository.save(salon);
        return salonMapper.toDto(salon);
    }
    @Override
    @Transactional(readOnly = true)
    public Optional<SalonDTO> getSalonById(Integer salonId) {
        return salonRepository.findById(salonId).map(salonMapper::toDto);
    }
    @Override
    @Transactional(readOnly = true)
    public List<SalonDTO> getSalonByUsername(String username){
        List<Salon> salones = salonRepository.findAllByUsuarioUsernameOrderByNombre(username);
        return salones.stream().map(salonMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public void delete(Integer salonId) {
        salonRepository.deleteById(salonId);
    }
    @Override
    public Salon parcial(Salon dto, Integer salonId){
        Salon salon = salonRepository.findById(salonId)
                .orElseThrow(() -> new RuntimeException("UsuarioRol no encontrado " + salonId));

        if (dto.getEstado() != null) {
            salon.setEstado(dto.getEstado());
        }
        return salonRepository.save(salon);
    }
}

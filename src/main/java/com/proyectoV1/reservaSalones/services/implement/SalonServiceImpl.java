package com.proyectoV1.reservaSalones.services.implement;

import com.proyectoV1.reservaSalones.domain.entities.Salon;

import com.proyectoV1.reservaSalones.domain.entities.Servicio;
import com.proyectoV1.reservaSalones.domain.entities.Usuario;
import com.proyectoV1.reservaSalones.dto.SalonDTO;
import com.proyectoV1.reservaSalones.repositories.SalonRepository;
import com.proyectoV1.reservaSalones.services.SalonService;
import com.proyectoV1.reservaSalones.services.mapper.SalonMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class SalonServiceImpl implements SalonService {
    private final SalonRepository salonRepository;
    private final SalonMapper salonMapper;

    public SalonServiceImpl(SalonRepository salonRepository, SalonMapper salonMapper) {
        this.salonRepository = salonRepository;
        this.salonMapper = salonMapper;
    }
    @Override
    @Transactional(readOnly = true)
    public List<SalonDTO> listarSalon() {
        return salonRepository.findAll()
                .stream()
                .map(salonMapper::toDto).collect(Collectors.toList());
    }
    @Override
    public SalonDTO save(SalonDTO dto) {
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
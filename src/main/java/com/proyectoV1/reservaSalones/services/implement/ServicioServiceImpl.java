package com.proyectoV1.reservaSalones.services.implement;

import com.proyectoV1.reservaSalones.domain.entities.Servicio;
import com.proyectoV1.reservaSalones.domain.entities.TipoSolicitudReserva;
import com.proyectoV1.reservaSalones.dto.ServicioDTO;
import com.proyectoV1.reservaSalones.dto.ServicioDTO;
import com.proyectoV1.reservaSalones.repositories.ServicioRepository;
import com.proyectoV1.reservaSalones.services.ServicioService;
import com.proyectoV1.reservaSalones.services.mapper.ServicioMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
@Transactional
public class ServicioServiceImpl implements ServicioService {
    private final ServicioRepository servicioRepository;
    private final ServicioMapper servicioMapper;

    public ServicioServiceImpl(ServicioRepository servicioRepository, ServicioMapper servicioMapper) {
        this.servicioRepository = servicioRepository;
        this.servicioMapper = servicioMapper;
    }
    @Override
    @Transactional(readOnly = true)
    public List<ServicioDTO> listarServicio() {
        return servicioRepository.findAll()
                .stream()
                .map(servicioMapper::toDto).collect(Collectors.toList());
    }
    @Override
    public ServicioDTO save(ServicioDTO dto) {
        Servicio servicio = servicioMapper.toEntity(dto);
        servicio.setCreated_at(LocalDateTime.now());
        servicio = servicioRepository.save(servicio);
        return servicioMapper.toDto(servicio);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ServicioDTO> getServicioById(Integer tipoSRId) {
        return servicioRepository.findById(tipoSRId).map(servicioMapper::toDto);
    }

    @Override
    public void delete(Integer tipoSRId) {
        servicioRepository.deleteById(tipoSRId);
    }
}

package com.proyectoV1.reservaSalones.services.implement;

import com.proyectoV1.reservaSalones.domain.entities.Servicio;
import com.proyectoV1.reservaSalones.domain.entities.TipoSolicitudReserva;
import com.proyectoV1.reservaSalones.dto.SalonDTO;
import com.proyectoV1.reservaSalones.dto.ServicioDTO;
import com.proyectoV1.reservaSalones.dto.ServicioDTO;
import com.proyectoV1.reservaSalones.repositories.ServicioRepository;
import com.proyectoV1.reservaSalones.services.ServicioService;
import com.proyectoV1.reservaSalones.services.mapper.ServicioMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
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
        return servicioRepository.findAllByOrderByNombre()
                .stream()
                .map(servicioMapper::toDto).collect(Collectors.toList());
    }
    @Override
    @Transactional(readOnly = true)
    public List<ServicioDTO> listarServicioHabilitado(){
        List<ServicioDTO> servicios = servicioRepository.findAllServicioActivo();
        return servicios;
    }
    @Override
    public ServicioDTO save(ServicioDTO dto) {
        Servicio servicio = servicioMapper.toEntity(dto);
        if (servicio.getCreated_at() == null) {
            servicio.setCreated_at(LocalDateTime.now());
        }
        servicio = servicioRepository.save(servicio);
        return servicioMapper.toDto(servicio);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ServicioDTO> getServicioById(Integer servicioId) {
        return servicioRepository.findById(servicioId).map(servicioMapper::toDto);
    }

    @Override
    public void delete(Integer servicioId) {
        servicioRepository.deleteById(servicioId);
    }
    @Override
    public Servicio parcial(ServicioDTO dto, Integer servicioId){
        Servicio servicio = servicioRepository.findById(servicioId)
                .orElseThrow(() -> new RuntimeException("Servicio no encontrado " + servicioId));

        if (dto.getEstado() != null) {
            servicio.setEstado(dto.getEstado());
        }
        return servicioRepository.save(servicio);
    }
    @Override
    public ServicioDTO findById(Integer servicioId) {
        Servicio servicio = servicioRepository.findById(servicioId)
                .orElseThrow(() -> new NoSuchElementException("Servicio no encontrado con ID: " + servicioId));

        ServicioDTO servicioDTO = new ServicioDTO();
        servicioDTO.setId(servicio.getId());
        servicioDTO.setNombre(servicio.getNombre());


        return servicioDTO;
    }
}

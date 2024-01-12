package com.proyectoV1.reservaSalones.services.implement;

import com.proyectoV1.reservaSalones.domain.entities.SolicitudReserva;
import com.proyectoV1.reservaSalones.dto.SolicitudReservaDTO;
import com.proyectoV1.reservaSalones.repositories.SolicitudReservaRepository;
import com.proyectoV1.reservaSalones.services.SolicitudReservaService;
import com.proyectoV1.reservaSalones.services.mapper.SolicitudReservaMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class SolicitudReservaServiceImpl implements SolicitudReservaService {
    private final SolicitudReservaRepository solicitudReservaRepository;
    private final SolicitudReservaMapper solicitudReservaMapper;

    public SolicitudReservaServiceImpl(SolicitudReservaRepository solicitudReservaRepository, SolicitudReservaMapper solicitudReservaMapper) {
        this.solicitudReservaRepository = solicitudReservaRepository;
        this.solicitudReservaMapper = solicitudReservaMapper;
    }
    @Override
    @Transactional(readOnly = true)
    public List<SolicitudReservaDTO> listarSolicitudReserva() {
        return solicitudReservaRepository.findAll()
                .stream()
                .map(solicitudReservaMapper::toDto).collect(Collectors.toList());
    }
    @Override
    public SolicitudReservaDTO save(SolicitudReservaDTO dto) {
        SolicitudReserva solicitudReserva = solicitudReservaMapper.toEntity(dto);
        solicitudReserva = solicitudReservaRepository.save(solicitudReserva);
        return solicitudReservaMapper.toDto(solicitudReserva);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<SolicitudReservaDTO> getSolicitudById(Long solicitudId) {
        return solicitudReservaRepository.findById(solicitudId).map(solicitudReservaMapper::toDto);
    }

    @Override
    public void delete(Long solicitudId) {
        solicitudReservaRepository.deleteById(solicitudId);
    }
}

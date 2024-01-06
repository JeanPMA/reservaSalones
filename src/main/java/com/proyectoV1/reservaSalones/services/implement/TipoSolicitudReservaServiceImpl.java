package com.proyectoV1.reservaSalones.services.implement;

import com.proyectoV1.reservaSalones.domain.entities.TipoSolicitudReserva;
import com.proyectoV1.reservaSalones.dto.TipoSolicitudReservaDTO;
import com.proyectoV1.reservaSalones.repositories.TipoSolicitudReservaRepository;
import com.proyectoV1.reservaSalones.services.TipoSolicitudReservaService;
import com.proyectoV1.reservaSalones.services.mapper.TipoSolicitudReservaMapper;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class TipoSolicitudReservaServiceImpl implements TipoSolicitudReservaService {
    private final TipoSolicitudReservaRepository tipoSolicitudReservaRepository;
    private final TipoSolicitudReservaMapper tipoSolicitudReservaMapper;


    public TipoSolicitudReservaServiceImpl(TipoSolicitudReservaRepository tipoSolicitudReservaRepository, TipoSolicitudReservaMapper tipoSolicitudReservaMapper) {
        this.tipoSolicitudReservaRepository = tipoSolicitudReservaRepository;
        this.tipoSolicitudReservaMapper = tipoSolicitudReservaMapper;
    }
    @Override
    @Transactional(readOnly = true)
    public List<TipoSolicitudReservaDTO> listarTipoSR() {
        return tipoSolicitudReservaRepository.findAll()
                .stream()
                .map(tipoSolicitudReservaMapper::toDto).collect(Collectors.toList());
    }
    @Override
    public TipoSolicitudReservaDTO save(TipoSolicitudReservaDTO dto) {
        TipoSolicitudReserva tipoSolicitudReserva = tipoSolicitudReservaMapper.toEntity(dto);
        tipoSolicitudReserva.setCreated_at(LocalDateTime.now());
        tipoSolicitudReserva = tipoSolicitudReservaRepository.save(tipoSolicitudReserva);
        return tipoSolicitudReservaMapper.toDto(tipoSolicitudReserva);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TipoSolicitudReservaDTO> getTipoSRById(Integer tipoSRId) {
        return tipoSolicitudReservaRepository.findById(tipoSRId).map(tipoSolicitudReservaMapper::toDto);
    }

    @Override
    public void delete(Integer tipoSRId) {
        tipoSolicitudReservaRepository.deleteById(tipoSRId);
    }
}

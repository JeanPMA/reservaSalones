package com.proyectoV1.reservaSalones.services.implement;

import com.proyectoV1.reservaSalones.domain.entities.Salon;
import com.proyectoV1.reservaSalones.domain.entities.SolicitudReserva;
import com.proyectoV1.reservaSalones.domain.entities.TipoSolicitudReserva;
import com.proyectoV1.reservaSalones.domain.entities.Usuario;
import com.proyectoV1.reservaSalones.dto.SolicitudReservaDTO;
import com.proyectoV1.reservaSalones.repositories.SolicitudReservaRepository;
import com.proyectoV1.reservaSalones.repositories.TipoSolicitudReservaRepository;
import com.proyectoV1.reservaSalones.repositories.UsuarioRepository;
import com.proyectoV1.reservaSalones.services.SolicitudReservaService;
import com.proyectoV1.reservaSalones.services.mapper.SolicitudReservaMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class SolicitudReservaServiceImpl implements SolicitudReservaService {
    private final SolicitudReservaRepository solicitudReservaRepository;
    private final SolicitudReservaMapper solicitudReservaMapper;
    private final UsuarioRepository usuarioRepository;
    private final TipoSolicitudReservaRepository tipoSolicitudReservaRepository;

    public SolicitudReservaServiceImpl(SolicitudReservaRepository solicitudReservaRepository, SolicitudReservaMapper solicitudReservaMapper, UsuarioRepository usuarioRepository, TipoSolicitudReservaRepository tipoSolicitudReservaRepository) {
        this.solicitudReservaRepository = solicitudReservaRepository;
        this.solicitudReservaMapper = solicitudReservaMapper;
        this.usuarioRepository = usuarioRepository;
        this.tipoSolicitudReservaRepository = tipoSolicitudReservaRepository;
    }
    @Override
    @Transactional(readOnly = true)
    public List<SolicitudReservaDTO> listarSolicitudReserva() {
        return solicitudReservaRepository.findAll()
                .stream()
                .map(solicitudReservaMapper::toDto).collect(Collectors.toList());
    }
    @Override
    public SolicitudReservaDTO save(SolicitudReservaDTO dto, String username) {
        SolicitudReserva solicitudReserva = solicitudReservaMapper.toEntity(dto);
        if (solicitudReserva.getFecha_emision() == null) {
            solicitudReserva.setFecha_emision(LocalDate.now());
        }
        Optional<Usuario> optionalUsuario = usuarioRepository.findOneByUsername(username);

        if (optionalUsuario.isPresent()) {
            solicitudReserva.setUsuario(optionalUsuario.get());
        } else {
            throw new RuntimeException("Usuario no encontrado: " + username);
        }

        if (dto.getTipoSR() == null) {
            Optional<TipoSolicitudReserva> tipoSolicitudPendiente = tipoSolicitudReservaRepository.findByNombre("PENDIENTE");

            if (tipoSolicitudPendiente.isPresent()) {
                solicitudReserva.setTipoSR(tipoSolicitudPendiente.get());
            } else {
                throw new RuntimeException("No se pudo encontrar el tipo de solicitud con nombre 'PENDIENTE'");
            }
        } else {

            solicitudReserva.setTipoSR(dto.getTipoSR());
        }

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

    @Override
    @Transactional(readOnly = true)
    public List<SolicitudReserva> findByTipoSRNotNombre(String username) {
        Optional<TipoSolicitudReserva> estadoInvisible = tipoSolicitudReservaRepository.findByNombre("INVISIBLE");

        if (estadoInvisible.isPresent()) {
            return solicitudReservaRepository.findByTipoSRNombreNotAndUsuarioUsernameOrderByFecha_emisionDesc(estadoInvisible.get().getNombre(), username);
        } else {
            throw new RuntimeException("ERROR AL OBTENER DATOS");
        }
    }
    @Override
    public SolicitudReserva calificacionReserva(SolicitudReserva dto, Long solicitudId){
        SolicitudReserva solicitudReserva = solicitudReservaRepository.findById(solicitudId)
                .orElseThrow(() -> new RuntimeException("solicitud-reserva no encontrado " + solicitudId));

        if (solicitudReserva.getPuntuacion() == null) {
            solicitudReserva.setPuntuacion(dto.getPuntuacion());
        }
        return solicitudReservaRepository.save(solicitudReserva);
    }
    @Override
    public List<SolicitudReservaDTO> obtenerSolicitudesPorSalonYUsuario(String username) {
        return solicitudReservaRepository.findSolicitudesBySalonUsuarioUsername(username);
    }
    @Override
    public List<SolicitudReservaDTO> obtenerReservasPorSalonYUsuario(String username) {
        return solicitudReservaRepository.findReservasBySalonUsuarioUsername(username);
    }
    @Override
    public SolicitudReservaDTO cambiarTipoSR(SolicitudReservaDTO dto, Long solicitudId){
        SolicitudReserva solicitudReserva = solicitudReservaRepository.findById(solicitudId)
                .orElseThrow(() -> new RuntimeException("solicitud-reserva no encontrado " + solicitudId));

        if (solicitudReserva.getTipoSR() != null) {
            solicitudReserva.setTipoSR(dto.getTipoSR());
        }
        SolicitudReserva solicitudReservaActualizada = solicitudReservaRepository.save(solicitudReserva);
        return solicitudReservaMapper.toDto(solicitudReservaActualizada);
    }

}

package com.proyectoV1.reservaSalones.services.implement;

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

        Optional<TipoSolicitudReserva> tipoSolicitudPendiente = tipoSolicitudReservaRepository.findByNombre("PENDIENTE");

        if (tipoSolicitudPendiente.isPresent()) {
            solicitudReserva.setTipoSR(tipoSolicitudPendiente.get());
        } else {
            throw new RuntimeException("No se pudo encontrar el tipo de solicitud con nombre 'PENDIENTE'");
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
            return solicitudReservaRepository.findByTipoSRNombreNotAndUsuarioUsername(estadoInvisible.get().getNombre(), username);
        } else {
            throw new RuntimeException("ERROR AL OBTENER DATOS");
        }
    }
}

package com.proyectoV1.reservaSalones.services.implement;

import com.proyectoV1.reservaSalones.domain.entities.TelefonoSalon;
import com.proyectoV1.reservaSalones.dto.TelefonoSalonDTO;
import com.proyectoV1.reservaSalones.repositories.TelefonoSalonRepository;
import com.proyectoV1.reservaSalones.services.TelefonoSalonService;
import com.proyectoV1.reservaSalones.services.mapper.TelefonoSalonMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class TelefonoSalonServiceImpl implements TelefonoSalonService {
    private final TelefonoSalonRepository telefonoSalonRepository;
    private final TelefonoSalonMapper telefonoSalonMapper;

    public TelefonoSalonServiceImpl(TelefonoSalonRepository telefonoSalonRepository, TelefonoSalonMapper telefonoSalonMapper) {
        this.telefonoSalonRepository = telefonoSalonRepository;
        this.telefonoSalonMapper = telefonoSalonMapper;
    }


    @Override
    @Transactional(readOnly = true)
    public List<TelefonoSalonDTO> listarTelefonoSalon() {
        return telefonoSalonRepository.findAll()
                .stream()
                .map(telefonoSalonMapper::toDto).collect(Collectors.toList());
    }
    @Override
    public TelefonoSalonDTO save(TelefonoSalonDTO dto) {
        TelefonoSalon telefonoSalon = telefonoSalonMapper.toEntity(dto);
        telefonoSalon = telefonoSalonRepository.save(telefonoSalon);
        return telefonoSalonMapper.toDto(telefonoSalon);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TelefonoSalonDTO> getTelefonoById(Long telefonoId) {
        return telefonoSalonRepository.findById(telefonoId).map(telefonoSalonMapper::toDto);
    }

    @Override
    public void delete(Long telefonoId) {
        telefonoSalonRepository.deleteById(telefonoId);
    }
}

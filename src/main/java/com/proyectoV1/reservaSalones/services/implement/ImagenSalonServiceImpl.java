package com.proyectoV1.reservaSalones.services.implement;

import com.proyectoV1.reservaSalones.domain.entities.ImagenSalon;
import com.proyectoV1.reservaSalones.domain.entities.Usuario;
import com.proyectoV1.reservaSalones.dto.ImagenSalonDTO;
import com.proyectoV1.reservaSalones.repositories.ImagenSalonRepository;
import com.proyectoV1.reservaSalones.services.ImagenSalonService;
import com.proyectoV1.reservaSalones.services.mapper.ImagenSalonMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
@Transactional
public class ImagenSalonServiceImpl implements ImagenSalonService {
    private final ImagenSalonRepository imagenSalonRepository;
    private final ImagenSalonMapper imagenSalonMapper;

    public ImagenSalonServiceImpl(ImagenSalonRepository imagenSalonRepository, ImagenSalonMapper imagenSalonMapper) {
        this.imagenSalonRepository = imagenSalonRepository;
        this.imagenSalonMapper = imagenSalonMapper;
    }
    @Override
    @Transactional(readOnly = true)
    public List<ImagenSalonDTO> listarImagenes() {
        return imagenSalonRepository.findAll()
                .stream()
                .map(imagenSalonMapper::toDto).collect(Collectors.toList());
    }
    @Override
    public ImagenSalonDTO save(ImagenSalonDTO dto) {
        ImagenSalon imagenSalon = imagenSalonMapper.toEntity(dto);
        imagenSalon = imagenSalonRepository.save(imagenSalon);
        return imagenSalonMapper.toDto(imagenSalon);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ImagenSalonDTO> getImagenById(Long imagenSalonId) {
        return imagenSalonRepository.findById(imagenSalonId).map(imagenSalonMapper::toDto);
    }

    @Override
    public void delete(Long imagenSalonId) {
        imagenSalonRepository.deleteById(imagenSalonId);
    }

}

package com.proyectoV1.reservaSalones.services;

import com.proyectoV1.reservaSalones.dto.TelefonoSalonDTO;

import java.util.List;
import java.util.Optional;

public interface TelefonoSalonService {
    List<TelefonoSalonDTO> listarTelefonoSalon();

    TelefonoSalonDTO save(TelefonoSalonDTO dto);
    Optional<TelefonoSalonDTO> getTelefonoById(Long telefonoId);
    void delete(Long telefonoId);
}

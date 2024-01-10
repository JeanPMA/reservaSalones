package com.proyectoV1.reservaSalones.web.rest;

import com.proyectoV1.reservaSalones.dto.TelefonoSalonDTO;
import com.proyectoV1.reservaSalones.services.TelefonoSalonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/v1/telefono-salon")
public class TelefonoSalonController {
    private final TelefonoSalonService telefonoSalonService;

    public TelefonoSalonController(TelefonoSalonService telefonoSalonService) {
        this.telefonoSalonService = telefonoSalonService;
    }
    @GetMapping
    public ResponseEntity<List<TelefonoSalonDTO>> listarTelefonoSalon() {
        return ResponseEntity.ok().body(telefonoSalonService.listarTelefonoSalon());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TelefonoSalonDTO> getTelefonoById(@PathVariable final Long id) {
        return ResponseEntity
                .ok()
                .body(telefonoSalonService.getTelefonoById(id).orElseThrow(() -> new IllegalArgumentException("Recurso no encontrado: " + id)));
    }

    @PostMapping
    public ResponseEntity<TelefonoSalonDTO> create(@RequestBody final TelefonoSalonDTO dto) throws URISyntaxException {
        if (dto.getId() != null) {
            throw new IllegalArgumentException("El telefono-salon no puede tener ya un id ingresado.");
        }

        TelefonoSalonDTO TelefonoSalonDTO = telefonoSalonService.save(dto);

        return ResponseEntity.created(new URI("/v1/telefono-salon/" + TelefonoSalonDTO.getId())).body(TelefonoSalonDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TelefonoSalonDTO> editTelefono(@RequestBody final TelefonoSalonDTO dto,
                                          @PathVariable final Long id) throws URISyntaxException {
        if (dto.getId() == null) {
            throw new IllegalArgumentException("Invalid telefono-salon id, valor nulo");
        }
        if (!Objects.equals(dto.getId(), id)) {
            throw new IllegalArgumentException("Invalid id");
        }

        return ResponseEntity
                .ok()
                .body(this.telefonoSalonService.save(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable final Long id) {
        telefonoSalonService.delete(id);

        return ResponseEntity.noContent().build();
    }
}

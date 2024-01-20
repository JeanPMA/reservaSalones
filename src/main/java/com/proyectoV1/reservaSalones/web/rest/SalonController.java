package com.proyectoV1.reservaSalones.web.rest;

import com.proyectoV1.reservaSalones.domain.entities.Salon;
import com.proyectoV1.reservaSalones.domain.entities.Servicio;
import com.proyectoV1.reservaSalones.dto.ImagenSalonDTO;
import com.proyectoV1.reservaSalones.dto.SalonDTO;
import com.proyectoV1.reservaSalones.services.SalonService;
import com.proyectoV1.reservaSalones.services.implement.CloudinaryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

@RestController
@RequestMapping("/v1/salon")
public class SalonController {
    @Autowired
    CloudinaryServiceImpl cloudinaryService;
    private final SalonService salonService;

    public SalonController(SalonService salonService) {
        this.salonService = salonService;
    }
    @GetMapping
    public ResponseEntity<List<SalonDTO>> listarUsuario() {
        return ResponseEntity.ok().body(salonService.listarSalon());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SalonDTO> getSalonById(@PathVariable final Integer id) {
        return ResponseEntity
                .ok()
                .body(salonService.getSalonById(id).orElseThrow(() -> new IllegalArgumentException("Recurso no encontrado: " + id)));
    }

    @PostMapping
    public ResponseEntity<SalonDTO> create(@RequestBody final SalonDTO dto) throws URISyntaxException {
        if (dto.getId() != null) {
            throw new IllegalArgumentException("El salon no puede tener ya un id ingresado.");
        }

        SalonDTO salonDTO = salonService.save(dto);

        return ResponseEntity.created(new URI("/v1/salon/" + salonDTO.getId())).body(salonDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SalonDTO> editUsuario(@RequestBody final SalonDTO dto,
                                                  @PathVariable final Integer id) throws URISyntaxException {
        if (dto.getId() == null) {
            throw new IllegalArgumentException("Invalid salon id, valor nulo");
        }
        if (!Objects.equals(dto.getId(), id)) {
            throw new IllegalArgumentException("Invalid id");
        }

        return ResponseEntity
                .ok()
                .body(this.salonService.save(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable final Integer id) throws IOException {
        SalonDTO salonDTO = salonService.getSalonById(id).get();
        Map result = cloudinaryService.delete(salonDTO.getBanner_id());
        salonService.delete(id);

        return ResponseEntity.noContent().build();
    }
    @PatchMapping("/{id}")
    public ResponseEntity<Salon> actualizarParcial(@RequestBody Salon dto, @PathVariable final Integer id) {
        if (dto.getId() == null) {
            throw new IllegalArgumentException("Invalid salon id, valor nulo");
        }
        if (!Objects.equals(dto.getId(), id)) {
            throw new IllegalArgumentException("Invalid id");
        }
        salonService.parcial(dto, id);

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}

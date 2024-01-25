package com.proyectoV1.reservaSalones.web.rest;

import com.proyectoV1.reservaSalones.dto.ServicioDTO;
import com.proyectoV1.reservaSalones.services.ServicioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/v1/servicio")
public class ServicioController {
    private final ServicioService servicioService;

    public ServicioController(ServicioService servicioService) {
        this.servicioService = servicioService;
    }
    @GetMapping
    @PreAuthorize("hasAnyRole('OWNER','ADMIN')")
    public ResponseEntity<List<ServicioDTO>> listarServicio() {
        return ResponseEntity.ok().body(servicioService.listarServicio());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ServicioDTO> getServicioById(@PathVariable final Integer id) {
        return ResponseEntity
                .ok()
                .body(servicioService.getServicioById(id).orElseThrow(() -> new IllegalArgumentException("Recurso no encontrado: " + id)));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ServicioDTO> create(@RequestBody final ServicioDTO dto) throws URISyntaxException {
        if (dto.getId() != null) {
            throw new IllegalArgumentException("El servicio no puede tener ya un id ingresado.");
        }

        ServicioDTO ServicioDTO = servicioService.save(dto);

        return ResponseEntity.created(new URI("/v1/servicio/" + ServicioDTO.getId())).body(ServicioDTO);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ServicioDTO> editServicio(@RequestBody final ServicioDTO dto,
                                                           @PathVariable final Integer id) throws URISyntaxException {
        if (dto.getId() == null) {
            throw new IllegalArgumentException("Invalid servicio id, valor nulo");
        }
        if (!Objects.equals(dto.getId(), id)) {
            throw new IllegalArgumentException("Invalid id");
        }

        return ResponseEntity
                .ok()
                .body(this.servicioService.save(dto));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable final Integer id) {
        servicioService.delete(id);

        return ResponseEntity.noContent().build();
    }
    @PatchMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ServicioDTO> actualizarParcial(@RequestBody ServicioDTO dto, @PathVariable final Integer id) {
        if (dto.getId() == null) {
            throw new IllegalArgumentException("Invalid servicio id, valor nulo");
        }
        if (!Objects.equals(dto.getId(), id)) {
            throw new IllegalArgumentException("Invalid id");
        }
        servicioService.parcial(dto, id);

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}

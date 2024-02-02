package com.proyectoV1.reservaSalones.web.rest;

import com.proyectoV1.reservaSalones.dto.TipoSolicitudReservaDTO;
import com.proyectoV1.reservaSalones.services.TipoSolicitudReservaService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/v1/tipo-sr")
public class TipoSolicitudReservaController {
    private final TipoSolicitudReservaService tipoSolicitudReservaService;

    public TipoSolicitudReservaController(TipoSolicitudReservaService tipoSolicitudReservaService) {
        this.tipoSolicitudReservaService = tipoSolicitudReservaService;
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','USER','OWNER')")
    public ResponseEntity<List<TipoSolicitudReservaDTO>> listarTipoSR() {
        return ResponseEntity.ok().body(tipoSolicitudReservaService.listarTipoSR());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TipoSolicitudReservaDTO> getTipoSRById(@PathVariable final Integer id) {
        return ResponseEntity
                .ok()
                .body(tipoSolicitudReservaService.getTipoSRById(id).orElseThrow(() -> new IllegalArgumentException("Recurso no encontrado: " + id)));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TipoSolicitudReservaDTO> create(@RequestBody final TipoSolicitudReservaDTO dto) throws URISyntaxException {
        if (dto.getId() != null) {
            throw new IllegalArgumentException("Tipo solicitud-reserva no puede tener ya un id ingresado.");
        }

        TipoSolicitudReservaDTO tipoSolicitudReservaDTO = tipoSolicitudReservaService.save(dto);

        return ResponseEntity.created(new URI("/v1/tipo-sr/" + tipoSolicitudReservaDTO.getId())).body(tipoSolicitudReservaDTO);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TipoSolicitudReservaDTO> editRol(@RequestBody final TipoSolicitudReservaDTO dto,
                                          @PathVariable final Integer id) throws URISyntaxException {
        if (dto.getId() == null) {
            throw new IllegalArgumentException("Invalid tipo solicitud-reserva id, valor nulo");
        }
        if (!Objects.equals(dto.getId(), id)) {
            throw new IllegalArgumentException("Invalid id");
        }

        return ResponseEntity
                .ok()
                .body(this.tipoSolicitudReservaService.save(dto));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable final Integer id) {
        tipoSolicitudReservaService.delete(id);

        return ResponseEntity.noContent().build();
    }
}

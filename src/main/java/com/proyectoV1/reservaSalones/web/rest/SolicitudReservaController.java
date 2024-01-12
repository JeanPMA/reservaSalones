package com.proyectoV1.reservaSalones.web.rest;

import com.proyectoV1.reservaSalones.dto.SolicitudReservaDTO;
import com.proyectoV1.reservaSalones.dto.UsuarioDTO;
import com.proyectoV1.reservaSalones.services.SolicitudReservaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/v1/solicitud-reserva")
public class SolicitudReservaController {
    private final SolicitudReservaService solicitudReservaService;

    public SolicitudReservaController(SolicitudReservaService solicitudReservaService) {
        this.solicitudReservaService = solicitudReservaService;
    }
    @GetMapping
    public ResponseEntity<List<SolicitudReservaDTO>> listarSolicitudReserva() {
        return ResponseEntity.ok().body(solicitudReservaService.listarSolicitudReserva());
    }
    @GetMapping("/{id}")
    public ResponseEntity<SolicitudReservaDTO> getSolicitudById(@PathVariable final Long id) {
        return ResponseEntity
                .ok()
                .body(solicitudReservaService.getSolicitudById(id).orElseThrow(() -> new IllegalArgumentException("Recurso no encontrado: " + id)));
    }

    @PostMapping
    public ResponseEntity<SolicitudReservaDTO> create(@RequestBody final SolicitudReservaDTO dto) throws URISyntaxException {
        if (dto.getId() != null) {
            throw new IllegalArgumentException("La solicitud-reserva no puede tener ya un id ingresado.");
        }

        SolicitudReservaDTO SolicitudReservaDTO = solicitudReservaService.save(dto);

        return ResponseEntity.created(new URI("/v1/solicitud-reserva/" + SolicitudReservaDTO.getId())).body(SolicitudReservaDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SolicitudReservaDTO> editUsuario(@RequestBody final SolicitudReservaDTO dto,
                                                  @PathVariable final Long id) throws URISyntaxException {
        if (dto.getId() == null) {
            throw new IllegalArgumentException("Invalid solicitud-reserva id, valor nulo");
        }
        if (!Objects.equals(dto.getId(), id)) {
            throw new IllegalArgumentException("Invalid id");
        }

        return ResponseEntity
                .ok()
                .body(this.solicitudReservaService.save(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable final Long id) {
        solicitudReservaService.delete(id);

        return ResponseEntity.noContent().build();
    }
}

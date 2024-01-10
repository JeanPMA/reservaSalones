package com.proyectoV1.reservaSalones.web.rest;

import com.proyectoV1.reservaSalones.domain.entities.Usuario;
import com.proyectoV1.reservaSalones.dto.UsuarioDTO;
import com.proyectoV1.reservaSalones.services.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/v1/usuario")
public class UsuarioController {
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }
    @GetMapping
    public ResponseEntity<List<Usuario>> listarUsuario() {
        return ResponseEntity.ok().body(usuarioService.listarUsuario());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> getUsuarioById(@PathVariable final Long id) {
        return ResponseEntity
                .ok()
                .body(usuarioService.getUsuarioById(id).orElseThrow(() -> new IllegalArgumentException("Recurso no encontrado: " + id)));
    }

    @PostMapping
    public ResponseEntity<UsuarioDTO> create(@RequestBody final UsuarioDTO dto) throws URISyntaxException {
        if (dto.getId() != null) {
            throw new IllegalArgumentException("El usuario no puede tener ya un id ingresado.");
        }

        UsuarioDTO UsuarioDTO = usuarioService.save(dto);

        return ResponseEntity.created(new URI("/v1/usuario/" + UsuarioDTO.getId())).body(UsuarioDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> editUsuario(@RequestBody final UsuarioDTO dto,
                                                    @PathVariable final Long id) throws URISyntaxException {
        if (dto.getId() == null) {
            throw new IllegalArgumentException("Invalid usuario id, valor nulo");
        }
        if (!Objects.equals(dto.getId(), id)) {
            throw new IllegalArgumentException("Invalid id");
        }

        return ResponseEntity
                .ok()
                .body(this.usuarioService.save(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable final Long id) {
        usuarioService.delete(id);

        return ResponseEntity.noContent().build();
    }
}

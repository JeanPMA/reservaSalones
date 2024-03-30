package com.proyectoV1.reservaSalones.web.rest;

import com.proyectoV1.reservaSalones.domain.entities.Salon;
import com.proyectoV1.reservaSalones.domain.entities.Servicio;
import com.proyectoV1.reservaSalones.dto.SalonAvgDTO;
import com.proyectoV1.reservaSalones.dto.SalonDTO;
import com.proyectoV1.reservaSalones.services.SalonService;
import com.proyectoV1.reservaSalones.services.ServicioService;
import com.proyectoV1.reservaSalones.services.implement.CloudinaryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.proyectoV1.reservaSalones.services.mapper.ServicioMapper;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/salon")
public class SalonController {
    @Autowired
    CloudinaryServiceImpl cloudinaryService;
    private final SalonService salonService;
    private final ServicioService servicioService;
    private final ServicioMapper servicioMapper;
    public SalonController(SalonService salonService, ServicioService servicioService, ServicioMapper servicioMapper) {
        this.salonService = salonService;
        this.servicioService = servicioService;
        this.servicioMapper = servicioMapper;
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<List<SalonDTO>> listarSalon() {
        return ResponseEntity.ok().body(salonService.listarSalon());
    }
    @GetMapping("/recomendado")
    public List<SalonDTO> listarSalonRecomendado() {
        return salonService.listarSalonPorCalificacion();
    }
    @GetMapping("/forUser")
    public List<SalonDTO> listarSalonParaUsuarios() {
        return salonService.listarSalonParaUsuarios();
    }
    @GetMapping("/auth/recomendado")
    @PreAuthorize("hasRole('USER')")
    public List<SalonAvgDTO> listarSalonRecomendadoAuth() {
        return salonService.listarSalonPorCalificacionAuth();
    }
    @GetMapping("/auth/all")
    @PreAuthorize("hasRole('USER')")
    public List<SalonDTO> listarSalonParaUsuariosAuth() {
        return salonService.listarSalonParaUsuariosAuth();
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('OWNER','ADMIN','USER')")
    public ResponseEntity<SalonDTO> getSalonById(@PathVariable final Integer id) {
        return ResponseEntity
                .ok()
                .body(salonService.getSalonById(id).orElseThrow(() -> new IllegalArgumentException("Recurso no encontrado: " + id)));
    }
    @GetMapping("/listado")
    @PreAuthorize("hasRole('OWNER')")
    public List<SalonDTO> listarSalonesById(@RequestParam String username) {
        return salonService.getSalonByUsername(username);
    }
    @PostMapping
    @PreAuthorize("hasAnyRole('OWNER','ADMIN')")
    public ResponseEntity<SalonDTO> create(@RequestParam("multipartFile") MultipartFile multipartFile, @ModelAttribute SalonDTO dto, @RequestParam String username) throws URISyntaxException, IOException {

        BufferedImage bufferedImage = ImageIO.read(multipartFile.getInputStream());
        if (dto.getId() != null) {
            throw new IllegalArgumentException("El salon no puede tener ya un id ingresado.");
        }
        Map result = cloudinaryService.upload(multipartFile);
        dto.setBanner_id((String) result.get("public_id"));
        dto.setBanner_url((String) result.get("url"));
        SalonDTO salonDTO = salonService.save(dto, username);

        return ResponseEntity.created(new URI("/v1/salon/" + salonDTO.getId())).body(salonDTO);
    }
    @PostMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<SalonDTO> create(@RequestParam("multipartFile") MultipartFile multipartFile, @ModelAttribute SalonDTO dto) throws URISyntaxException, IOException {

        BufferedImage bufferedImage = ImageIO.read(multipartFile.getInputStream());
        if (dto.getId() != null) {
            throw new IllegalArgumentException("El salon no puede tener ya un id ingresado.");
        }
        Map result = cloudinaryService.upload(multipartFile);
        dto.setBanner_id((String) result.get("public_id"));
        dto.setBanner_url((String) result.get("url"));
        SalonDTO salonDTO = salonService.saveForAdmin(dto);

        return ResponseEntity.created(new URI("/v1/salon/admin" + salonDTO.getId())).body(salonDTO);
    }
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('OWNER','ADMIN')")
    public SalonDTO editSalon(@RequestParam(required = false) MultipartFile multipartFile,
                                                  @RequestParam("servicios") Set<Integer> serviciosIds,
                                                  @ModelAttribute final SalonDTO dto,
                                                  @PathVariable final Integer id,
                                                  @RequestParam String username) throws URISyntaxException, IOException {
        if (dto.getId() == null) {
            throw new IllegalArgumentException("Invalid salon id, valor nulo");
        }
        if (!Objects.equals(dto.getId(), id)) {
            throw new IllegalArgumentException("Invalid id");
        }
        SalonDTO salonPresencia = salonService.getSalonById(id)
                .orElseThrow(() -> new IllegalArgumentException("Salón no encontrado con ID: " + id));

        salonPresencia.setNombre(dto.getNombre());
        salonPresencia.setDireccion(dto.getDireccion());
        salonPresencia.setCapacidad(dto.getCapacidad());
        salonPresencia.setDescripcion(dto.getDescripcion());
        salonPresencia.setTarifa(dto.getTarifa());
        salonPresencia.setEstado(dto.getEstado());
        salonPresencia.setUsuario(dto.getUsuario());



        Set<Servicio> servicios = serviciosIds.stream()
                .map(servicioId -> servicioService.getServicioById(servicioId)
                        .orElseThrow(() -> new NoSuchElementException("Servicio no encontrado con ID: " + servicioId)))
                .map(servicioMapper::toEntity)
                .collect(Collectors.toSet());


        salonPresencia.setServicios(servicios);

        if (multipartFile != null && !multipartFile.isEmpty()) {
            if (salonPresencia.getBanner_id() != null) {
                cloudinaryService.delete(salonPresencia.getBanner_id());
            }

            Map<String, String> result = cloudinaryService.upload(multipartFile);
            salonPresencia.setBanner_id(result.get("public_id"));
            salonPresencia.setBanner_url(result.get("url"));
        }

        SalonDTO salonDTO = salonService.save(salonPresencia, username);
        return salonDTO;
    }
    @PutMapping("/admin/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public SalonDTO editSalonForAdmin(@RequestParam(required = false) MultipartFile multipartFile,
                              @RequestParam("servicios") Set<Integer> serviciosIds,
                              @ModelAttribute final SalonDTO dto,
                              @PathVariable final Integer id) throws URISyntaxException, IOException {
        if (dto.getId() == null) {
            throw new IllegalArgumentException("Invalid salon id, valor nulo");
        }
        if (!Objects.equals(dto.getId(), id)) {
            throw new IllegalArgumentException("Invalid id");
        }
        SalonDTO salonPresencia = salonService.getSalonById(id)
                .orElseThrow(() -> new IllegalArgumentException("Salón no encontrado con ID: " + id));

        salonPresencia.setNombre(dto.getNombre());
        salonPresencia.setDireccion(dto.getDireccion());
        salonPresencia.setCapacidad(dto.getCapacidad());
        salonPresencia.setDescripcion(dto.getDescripcion());
        salonPresencia.setTarifa(dto.getTarifa());
        salonPresencia.setEstado(dto.getEstado());
        salonPresencia.setUsuario(dto.getUsuario());

        Set<Servicio> servicios = serviciosIds.stream()
                .map(servicioId -> servicioService.getServicioById(servicioId)
                        .orElseThrow(() -> new NoSuchElementException("Servicio no encontrado con ID: " + servicioId)))
                .map(servicioMapper::toEntity)
                .collect(Collectors.toSet());

        salonPresencia.setServicios(servicios);


        if (multipartFile != null && !multipartFile.isEmpty()) {
            if (salonPresencia.getBanner_id() != null) {
                cloudinaryService.delete(salonPresencia.getBanner_id());
            }

            Map<String, String> result = cloudinaryService.upload(multipartFile);
            salonPresencia.setBanner_id(result.get("public_id"));
            salonPresencia.setBanner_url(result.get("url"));
        }

        SalonDTO salonDTO = salonService.saveForAdmin(salonPresencia);
        return salonDTO;
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('OWNER','ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable final Integer id) throws IOException {
        SalonDTO salonDTO = salonService.getSalonById(id).get();
        Map result = cloudinaryService.delete(salonDTO.getBanner_id());
        salonService.delete(id);

        return ResponseEntity.noContent().build();
    }
    @PatchMapping("/{id}")
    @PreAuthorize("hasAnyRole('OWNER','ADMIN')")
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

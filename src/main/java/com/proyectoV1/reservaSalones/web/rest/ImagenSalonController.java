package com.proyectoV1.reservaSalones.web.rest;

import com.proyectoV1.reservaSalones.services.implement.CloudinaryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/v1/imagen-salon")
@CrossOrigin
public class ImagenSalonController {
    @Autowired
    CloudinaryServiceImpl cloudinaryService;
    @PostMapping
    public ResponseEntity<Map> crear(@RequestParam MultipartFile multipartFile) throws IOException{
        Map result = cloudinaryService.upload(multipartFile);
        return new ResponseEntity(result, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Map> eliminar(@PathVariable("id") String id) throws IOException{
        Map result = cloudinaryService.delete(id);
        return new ResponseEntity(result, HttpStatus.OK);
    }

}

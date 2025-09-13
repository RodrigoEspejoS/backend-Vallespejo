package com.example.back_vallespejo.controladores;

import com.example.back_vallespejo.models.dto.ProyectoDTO;
import com.example.back_vallespejo.models.dto.ProyectoCompletoDTO;
import com.example.back_vallespejo.models.entities.Proyecto;
import com.example.back_vallespejo.service.IProyectoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ProyectoController {

    @Autowired
    private IProyectoService proyectoService;

    @PostMapping("/proyecto/crear")
    @ResponseStatus(HttpStatus.CREATED)
    public Proyecto crearProyectoConDTO(@RequestBody ProyectoDTO proyectoDTO){
        return proyectoService.crearProyectoDesdeDTO(proyectoDTO);
    }

    @DeleteMapping("/proyecto/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarProyecto(@PathVariable Long id){
        Proyecto proyecto = proyectoService.findById(id);
        if(proyecto != null){
            proyectoService.delete(proyecto);
        }
    }

    @GetMapping("/proyecto/{id}")
    @Transactional(readOnly = true)
    public ProyectoCompletoDTO getProyectoCompleto(@PathVariable Long id){
        return proyectoService.getProyectoCompleto(id);
    }
}

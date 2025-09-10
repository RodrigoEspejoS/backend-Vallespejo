package com.example.back_vallespejo.controladores;

import com.example.back_vallespejo.models.entities.Rol;
import com.example.back_vallespejo.services.IRolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class RolController {

    @Autowired
    private IRolService rolService;

    /*Get para obtener todos los roles*/
    @GetMapping("/rol")
    public List<Rol> getAll(){
        return rolService.getAll();
    }

    @PostMapping("/rol")
    @ResponseStatus(HttpStatus.CREATED)
    public Rol registrarRol(@RequestBody Rol rol){
        return rolService.registrarRol(rol);
    }

    @GetMapping("/rol/{id}")
    public Rol getRolById(@PathVariable Long id){
        return rolService.findById(id);
    }


    @DeleteMapping("/rol/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarRol(@PathVariable Long id){
        Rol rol = rolService.findById(id);
        if(rol != null){
            rolService.delete(rol);
        }
    }
}

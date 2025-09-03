package com.example.back_vallespejo.controladores;

import com.example.back_vallespejo.models.entities.Material;
import com.example.back_vallespejo.service.IMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class MaterialController {

    @Autowired
    private IMaterialService materialService;

    
    @GetMapping("/material")
    public List<Material> getAll(){
        return materialService.getAll();
    }

    @PostMapping("/material")
    @ResponseStatus(HttpStatus.CREATED)
    public Material registrarMaterial(@RequestBody Material material){
        return materialService.registrarMaterial(material);
    }

    @GetMapping("/material/{id}")
    public Material getMaterialById(@PathVariable Long id){
        return materialService.findById(id);
    }

    @DeleteMapping("/material/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarMaterial(@PathVariable Long id){
        Material material = materialService.findById(id);
        if(material != null){
            materialService.delete(material);
        }
    }

    /*Nota: Rutas de Put para gestionar cantidades o colocar una cantidad especifica*/
    @PutMapping("/material/{id}/cantidad")
    public Material actualizarCantidad(@PathVariable Long id, @RequestParam Integer cantidad){
        return materialService.actualizarCantidad(id, cantidad);
    }

    @PutMapping("/material/{id}/agregar-cantidad")
    public Material agregarCantidad(@PathVariable Long id, @RequestParam Integer cantidad){
        return materialService.agregarCantidad(id, cantidad);
    }

    @PutMapping("/material/{id}/reducir-cantidad")
    public Material reducirCantidad(@PathVariable Long id, @RequestParam Integer cantidad){
        return materialService.reducirCantidad(id, cantidad);
    }
}

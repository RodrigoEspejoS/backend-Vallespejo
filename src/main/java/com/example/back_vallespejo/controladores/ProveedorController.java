package com.example.back_vallespejo.controladores;

import com.example.back_vallespejo.models.entities.Proveedor;
import com.example.back_vallespejo.service.IProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ProveedorController {

    @Autowired
    private IProveedorService proveedorService;

    /*Get para obtener todos los proveedores*/
    @GetMapping("/proveedor")
    public List<Proveedor> getAll(){
        return proveedorService.getAll();
    }

    @PostMapping("/proveedor")
    @ResponseStatus(HttpStatus.CREATED)
    public Proveedor registrarProveedor(@RequestBody Proveedor proveedor){
        return proveedorService.registrarProveedor(proveedor);
    }

    @GetMapping("/proveedor/{id}")
    public Proveedor getProveedorById(@PathVariable Long id){
        return proveedorService.findById(id);
    }

    @DeleteMapping("/proveedor/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarProveedor(@PathVariable Long id){
        Proveedor proveedor = proveedorService.findById(id);
        if(proveedor != null){
            proveedorService.delete(proveedor);
        }
    }
}

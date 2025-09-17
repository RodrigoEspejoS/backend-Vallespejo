package com.example.back_vallespejo.controladores;

import com.example.back_vallespejo.models.entities.TD_Presupuestos;
import com.example.back_vallespejo.service.ITDPresupuestosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/td-presupuestos")
@CrossOrigin
public class TDPresupuestosController {

    @Autowired
    private ITDPresupuestosService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TD_Presupuestos crear(@RequestBody TD_Presupuestos dto) {
        return service.crear(dto);
    }

    @GetMapping
    public List<TD_Presupuestos> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public TD_Presupuestos obtener(@PathVariable Long id) {
        return service.obtener(id).orElseThrow(() -> new RuntimeException("TD_Presupuestos no encontrado"));
    }

    @GetMapping("/codigo/{codigo}")
    public TD_Presupuestos obtenerPorCodigo(@PathVariable String codigo) {
        return service.obtenerPorCodigo(codigo).orElseThrow(() -> new RuntimeException("Código no encontrado"));
    }

    @PutMapping("/{id}")
    public TD_Presupuestos actualizar(@PathVariable Long id, @RequestBody TD_Presupuestos body) {
        return service.actualizar(id, body);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}

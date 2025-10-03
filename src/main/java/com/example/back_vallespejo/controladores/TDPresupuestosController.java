package com.example.back_vallespejo.controladores;

import com.example.back_vallespejo.models.entities.TD_Presupuestos;
import com.example.back_vallespejo.models.entities.U_Item_EquipoyHerramientas;
import com.example.back_vallespejo.models.entities.U_Item_ManodeObra;
import com.example.back_vallespejo.models.dao.IUItemEquipoyHerramientasDAO;
import com.example.back_vallespejo.models.dao.IUItemManodeObraDAO;
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
    
    @Autowired
    private IUItemEquipoyHerramientasDAO itemEquiposDAO;
    
    @Autowired
    private IUItemManodeObraDAO itemManoObraDAO;

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
        // Actualizar el TD_Presupuestos
        TD_Presupuestos actualizado = service.actualizar(id, body);
        
        // Actualizar automáticamente todos los items de equipos que referencian este TD_Presupuestos
        List<U_Item_EquipoyHerramientas> itemsEquipos = itemEquiposDAO.findByTdPresupuesto(actualizado);
        for (U_Item_EquipoyHerramientas item : itemsEquipos) {
            item.setCodigo(actualizado.getCodigo());
            item.setDesc_recurso(actualizado.getDesc_recurso());
            item.setUnidad(actualizado.getUnidad());
            item.setCuadrilla(actualizado.getCuadrilla());
            // NO actualizamos el precio unitario para mantener histórico de costos
            itemEquiposDAO.save(item);
        }
        
        // Actualizar automáticamente todos los items de mano de obra que referencian este TD_Presupuestos
        List<U_Item_ManodeObra> itemsManoObra = itemManoObraDAO.findByTdPresupuesto(actualizado);
        for (U_Item_ManodeObra item : itemsManoObra) {
            item.setCodigo(actualizado.getCodigo());
            item.setDesc_recurso(actualizado.getDesc_recurso());
            item.setUnidad(actualizado.getUnidad());
            item.setCuadrilla(actualizado.getCuadrilla());
            // NO actualizamos el precio unitario para mantener histórico de costos
            itemManoObraDAO.save(item);
        }
        
        return actualizado;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}

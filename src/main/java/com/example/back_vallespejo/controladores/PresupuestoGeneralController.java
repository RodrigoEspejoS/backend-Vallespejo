package com.example.back_vallespejo.controladores;

import com.example.back_vallespejo.service.IPresupuestoGeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class PresupuestoGeneralController {

    @Autowired
    private IPresupuestoGeneralService presupuestoGeneralService;

    @GetMapping("/presupuesto-general/{id}/actividades/ids")
    public ResponseEntity<List<Long>> getActividadesIds(@PathVariable Long id) {
        List<Long> actividadesIds = presupuestoGeneralService.getActividadesIdsByPresupuestoId(id);
        
        if (actividadesIds.isEmpty()) {
            // Verificar si el presupuesto existe
            if (presupuestoGeneralService.findById(id) == null) {
                return ResponseEntity.notFound().build();
            }
            // El presupuesto existe pero no tiene actividades
            return ResponseEntity.ok(actividadesIds);
        }
        
        return ResponseEntity.ok(actividadesIds);
    }
}
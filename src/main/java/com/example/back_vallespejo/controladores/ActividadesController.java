package com.example.back_vallespejo.controladores;

import com.example.back_vallespejo.models.dto.RendimientoInfoDTO;
import com.example.back_vallespejo.models.entities.Actividades;
import com.example.back_vallespejo.models.entities.Presupuesto_unitario;
import com.example.back_vallespejo.service.IActividadesService;
import com.example.back_vallespejo.service.IPresupuestoUnitarioService;
import com.example.back_vallespejo.service.RendimientoCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ActividadesController {

    @Autowired
    private IActividadesService actividadesService;

    @Autowired
    private IPresupuestoUnitarioService presupuestoUnitarioService;

    @Autowired
    private RendimientoCalculator rendimientoCalculator;

    /**
     * Recalcula automáticamente el rendimiento de una actividad existente
     * basándose en su nombre y descripción actuales.
     */
    @PutMapping("/actividad/{id}/recalcular-rendimiento")
    public ResponseEntity<RendimientoInfoDTO> recalcularRendimiento(@PathVariable Long id) {
        Actividades actividad = actividadesService.findById(id);
        
        if (actividad == null) {
            return ResponseEntity.notFound().build();
        }

        Presupuesto_unitario presupuestoUnitario = actividad.getPresupuestoUnitario();
        if (presupuestoUnitario == null) {
            return ResponseEntity.badRequest().build();
        }

        // Calcular nuevo rendimiento
        RendimientoInfoDTO rendimientoInfo = rendimientoCalculator.calcularRendimientoConInfo(
            actividad.getNombre(),
            actividad.getDescripcion()
        );

        // Actualizar el presupuesto unitario
        presupuestoUnitario.setU_rendimiento(rendimientoInfo.getRendimiento());
        presupuestoUnitario.setUnidad(rendimientoInfo.getUnidad());
        presupuestoUnitario.setT_rendimiento(rendimientoInfo.getTiempoRendimiento());

        // Guardar cambios
        presupuestoUnitarioService.registrar(presupuestoUnitario);

        return ResponseEntity.ok(rendimientoInfo);
    }

    /**
     * Actualiza manualmente el rendimiento de una actividad.
     * Permite sobrescribir el cálculo automático si el usuario lo necesita.
     */
    @PutMapping("/actividad/{id}/actualizar-rendimiento")
    public ResponseEntity<String> actualizarRendimientoManual(
            @PathVariable Long id,
            @RequestBody Map<String, Object> rendimientoData) {
        
        Actividades actividad = actividadesService.findById(id);
        
        if (actividad == null) {
            return ResponseEntity.notFound().build();
        }

        Presupuesto_unitario presupuestoUnitario = actividad.getPresupuestoUnitario();
        if (presupuestoUnitario == null) {
            return ResponseEntity.badRequest().build();
        }

        try {
            // Actualizar rendimiento si se proporciona
            if (rendimientoData.containsKey("rendimiento")) {
                Double nuevoRendimiento = Double.valueOf(rendimientoData.get("rendimiento").toString());
                presupuestoUnitario.setU_rendimiento(nuevoRendimiento);
            }

            // Actualizar unidad si se proporciona
            if (rendimientoData.containsKey("unidad")) {
                String nuevaUnidad = rendimientoData.get("unidad").toString();
                presupuestoUnitario.setUnidad(nuevaUnidad);
            }

            // Actualizar tiempo de rendimiento si se proporciona
            if (rendimientoData.containsKey("tiempoRendimiento")) {
                String nuevoTiempo = rendimientoData.get("tiempoRendimiento").toString();
                presupuestoUnitario.setT_rendimiento(nuevoTiempo);
            }

            // Guardar cambios
            presupuestoUnitarioService.registrar(presupuestoUnitario);

            return ResponseEntity.ok("Rendimiento actualizado correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error al actualizar rendimiento: " + e.getMessage());
        }
    }

    /**
     * Obtiene información de la actividad incluyendo su rendimiento actual.
     */
    @GetMapping("/actividad/{id}/info-rendimiento")
    public ResponseEntity<Map<String, Object>> obtenerInfoRendimiento(@PathVariable Long id) {
        Actividades actividad = actividadesService.findById(id);
        
        if (actividad == null) {
            return ResponseEntity.notFound().build();
        }

        Presupuesto_unitario presupuestoUnitario = actividad.getPresupuestoUnitario();
        if (presupuestoUnitario == null) {
            return ResponseEntity.badRequest().build();
        }

        Map<String, Object> info = Map.of(
            "actividadId", actividad.getId(),
            "nombreActividad", actividad.getNombre(),
            "descripcion", actividad.getDescripcion(),
            "rendimientoActual", presupuestoUnitario.getU_rendimiento() != null ? presupuestoUnitario.getU_rendimiento() : 0.0,
            "unidad", presupuestoUnitario.getUnidad() != null ? presupuestoUnitario.getUnidad() : "N/A",
            "tiempoRendimiento", presupuestoUnitario.getT_rendimiento() != null ? presupuestoUnitario.getT_rendimiento() : "N/A",
            "totalPresupuestoUnitario", presupuestoUnitario.getTotal_presupuesto_unitario(),
            "totalPresupuestoParcial", presupuestoUnitario.getTotal_presupuesto_parcial()
        );

        return ResponseEntity.ok(info);
    }
}

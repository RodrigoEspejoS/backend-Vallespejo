package com.example.back_vallespejo.controladores;

import com.example.back_vallespejo.models.dto.ProyectoDTO;
import com.example.back_vallespejo.models.dto.ProyectoCompletoDTO;
import com.example.back_vallespejo.models.dto.RendimientoInfoDTO;
import com.example.back_vallespejo.models.entities.Proyecto;
import com.example.back_vallespejo.service.IProyectoService;
import com.example.back_vallespejo.service.RendimientoCalculator;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.springframework.transaction.annotation.Transactional;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ProyectoController {

    
    @Autowired
    private IProyectoService proyectoService;

    @Autowired
    private RendimientoCalculator rendimientoCalculator;


    @PostMapping("/proyecto/{id}/agregar-actividad")
    @ResponseStatus(HttpStatus.CREATED)
    public String agregarActividadAProyecto(@PathVariable Long id, @RequestParam String nombreActividad) {
        boolean ok = proyectoService.agregarActividadAProyecto(id, nombreActividad);
        return ok ? "Actividad agregada correctamente" : "No se pudo agregar la actividad";
    }

    @PostMapping("/proyecto/crear")
    @ResponseStatus(HttpStatus.CREATED)
    public Proyecto crearProyectoConDTO(@Valid @RequestBody ProyectoDTO proyectoDTO){
        return proyectoService.crearProyectoDesdeDTO(proyectoDTO);
    }

    @PutMapping("/proyecto/{id}")
    public ResponseEntity<Proyecto> editarProyecto(@PathVariable Long id, @Valid @RequestBody ProyectoDTO proyectoDTO){
        try {
            Proyecto proyectoActualizado = proyectoService.updateProyecto(id, proyectoDTO);
            
            if(proyectoActualizado == null){
                return ResponseEntity.notFound().build();
            }
            
            return ResponseEntity.ok(proyectoActualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
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

    @GetMapping("/proyecto/todos/{idUsuarioCreador}")
    public ResponseEntity<List<ProyectoCompletoDTO>> getProyectosByUsuario(@PathVariable Long idUsuarioCreador){
        List<ProyectoCompletoDTO> proyectos = proyectoService.findByUsuarioResponsableId(idUsuarioCreador);
        return ResponseEntity.ok(proyectos);
    }

    /**
     * Endpoint para obtener una estimación del rendimiento antes de crear la actividad.
     * Útil para que el usuario sepa qué rendimiento se calculará automáticamente.
     */
    @PostMapping("/proyecto/calcular-rendimiento")
    public ResponseEntity<RendimientoInfoDTO> calcularRendimiento(@RequestBody Map<String, String> request) {
        String nombreActividad = request.get("nombreActividad");
        String descripcion = request.getOrDefault("descripcion", "");
        
        if (nombreActividad == null || nombreActividad.trim().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        
        RendimientoInfoDTO rendimientoInfo = rendimientoCalculator.calcularRendimientoConInfo(
            nombreActividad, 
            descripcion
        );
        
        return ResponseEntity.ok(rendimientoInfo);
    }
}

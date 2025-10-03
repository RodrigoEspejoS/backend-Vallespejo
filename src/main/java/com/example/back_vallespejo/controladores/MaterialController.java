package com.example.back_vallespejo.controladores;

import com.example.back_vallespejo.models.dao.IItemMaterialDAO;
import com.example.back_vallespejo.models.entities.ItemMaterial;
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

    @Autowired
    private IItemMaterialDAO itemMaterialDAO;

    
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

    @PutMapping("/material/{id}")
    public Material actualizarMaterial(@PathVariable Long id, @RequestBody Material materialActualizado){
        Material material = materialService.findById(id);
        if(material != null){
            if(materialActualizado.getNombre() != null){
                material.setNombre(materialActualizado.getNombre());
            }
            if(materialActualizado.getDescripcion() != null){
                material.setDescripcion(materialActualizado.getDescripcion());
            }
            if(materialActualizado.getUnidades() != null){
                material.setUnidades(materialActualizado.getUnidades());
            }
            if(materialActualizado.getCostoUnitario() != null){
                material.setCostoUnitario(materialActualizado.getCostoUnitario());
            }
            
            // Guardar el material actualizado
            Material materialGuardado = materialService.registrarMaterial(material);
            
            // Actualizar automáticamente todos los ItemMaterial que referencian este Material
            List<ItemMaterial> itemsMaterial = itemMaterialDAO.findByMaterial(materialGuardado);
            for (ItemMaterial item : itemsMaterial) {
                // Actualizar desc_recurso si se actualizó el nombre o descripción del material
                if(materialActualizado.getNombre() != null || materialActualizado.getDescripcion() != null){
                    // Usamos el nombre del material como desc_recurso
                    item.setDesc_recurso(materialGuardado.getNombre());
                }
                // NO actualizamos el precioUnitarioRecurso para mantener histórico de costos
                itemMaterialDAO.save(item);
            }
            
            return materialGuardado;
        }
        return null;
    }
}

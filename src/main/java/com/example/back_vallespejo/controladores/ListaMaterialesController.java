package com.example.back_vallespejo.controladores;

import com.example.back_vallespejo.models.dto.ItemMaterialDTO;
import com.example.back_vallespejo.models.dto.ItemMaterialResponseDTO;
import com.example.back_vallespejo.models.entities.ListaMateriales;
import com.example.back_vallespejo.models.entities.ItemMaterial;
import com.example.back_vallespejo.models.entities.Material;
import com.example.back_vallespejo.service.IListaMaterialesService;
import com.example.back_vallespejo.service.IItemMaterialService;
import com.example.back_vallespejo.service.IMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ListaMaterialesController {

    @Autowired
    private IListaMaterialesService listaMaterialesService;

    @Autowired
    private IItemMaterialService itemMaterialService;

    @Autowired
    private IMaterialService materialService;

    @DeleteMapping("/lista-materiales/{id}")
    @Transactional
    public void eliminarListaMateriales(@PathVariable Long id){
        try {
            ListaMateriales listaMateriales = listaMaterialesService.findById(id);

            listaMaterialesService.delete(listaMateriales);
                
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar la lista de materiales: " + e.getMessage());
        }
    }

    // Endpoint para agregar un item a una lista de materiales existente
    @PostMapping("/lista-materiales/{listaId}")
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional
    public ItemMaterialResponseDTO agregarItemALista(@PathVariable Long listaId, @RequestBody ItemMaterialDTO itemDTO){
        // Buscar la lista de materiales
        ListaMateriales listaMateriales = listaMaterialesService.findById(listaId);

        // Buscar el material
        Material material = materialService.findById(itemDTO.getMaterialId());

        // Crear el nuevo item
        ItemMaterial nuevoItem = new ItemMaterial();
        nuevoItem.setListaMateriales(listaMateriales);
        nuevoItem.setMaterial(material);
        nuevoItem.setCantidad(itemDTO.getCantidad());
        nuevoItem.setObservaciones(itemDTO.getObservaciones());
        nuevoItem.setFechaAgregado(LocalDateTime.now());

        // Guardar el item
        ItemMaterial itemGuardado = itemMaterialService.registrarItemMaterial(nuevoItem);

        // Actualizar la fecha de modificación de la lista
        listaMateriales.actualizarFechaModificacion();
        listaMaterialesService.registrarListaMateriales(listaMateriales);

        // Crear DTO de respuesta para evitar problemas de serialización
        return new ItemMaterialResponseDTO(
            itemGuardado.getId(),
            material.getNombre(),
            material.getSerie(),
            itemGuardado.getCantidad(),
            itemGuardado.getPrecioUnitario(),
            itemGuardado.getSubtotal(),
            itemGuardado.getObservaciones(),
            itemGuardado.getFechaAgregado(),
            listaMateriales.getId(),
            listaMateriales.getNombre()
        );
    }

    // Endpoint para obtener todos los items de una lista específica
    @GetMapping("/lista-materiales/{listaId}")
    @Transactional(readOnly = true)
    public List<ItemMaterialResponseDTO> getItemsPorLista(@PathVariable Long listaId){
        ListaMateriales listaMateriales = listaMaterialesService.findById(listaId);
        if(listaMateriales == null){
            throw new RuntimeException("Lista de materiales no encontrada con ID: " + listaId);
        }
        List<ItemMaterial> items = itemMaterialService.findByListaMateriales(listaMateriales);
        
        // Convertir a DTOs para evitar problemas de serialización
        return items.stream()
            .map(item -> new ItemMaterialResponseDTO(
                item.getId(),
                item.getMaterial().getNombre(),
                item.getMaterial().getSerie(),
                item.getCantidad(),
                item.getPrecioUnitario(),
                item.getSubtotal(),
                item.getObservaciones(),
                item.getFechaAgregado(),
                item.getListaMateriales().getId(),
                item.getListaMateriales().getNombre()
            ))
            .collect(Collectors.toList());
    }
}

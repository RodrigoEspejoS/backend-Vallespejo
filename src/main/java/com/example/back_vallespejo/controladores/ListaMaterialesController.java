package com.example.back_vallespejo.controladores;

import com.example.back_vallespejo.models.dto.U_ItemListaMaterialesDTO;
import com.example.back_vallespejo.models.dto.U_ItemListaMaterialesResponseDTO;
import com.example.back_vallespejo.models.entities.U_ItemListaMateriales;
import com.example.back_vallespejo.models.entities.U_ListaMateriales;
import com.example.back_vallespejo.models.entities.Material;
import com.example.back_vallespejo.services.IU_ItemListaMaterialesService;
import com.example.back_vallespejo.services.IU_ListaMaterialesService;
import com.example.back_vallespejo.services.IMaterialService;
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
    private IU_ListaMaterialesService listaMaterialesService;

    @Autowired
    private IU_ItemListaMaterialesService itemMaterialService;

    @Autowired
    private IMaterialService materialService;

    @DeleteMapping("/lista-materiales/{id}")
    @Transactional
    public void eliminarListaMateriales(@PathVariable Long id){
        try {
            U_ListaMateriales listaMateriales = listaMaterialesService.findById(id);

            listaMaterialesService.delete(listaMateriales);
                
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar la lista de materiales: " + e.getMessage());
        }
    }

    // Endpoint para agregar un item a una lista de materiales existente
    @PostMapping("/lista-materiales/{listaId}")
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional
    public U_ItemListaMaterialesResponseDTO agregarItemALista(@PathVariable Long listaId, @RequestBody U_ItemListaMaterialesDTO itemDTO){
        // Buscar la lista de materiales
        U_ListaMateriales listaMateriales = listaMaterialesService.findById(listaId);

        // Buscar el material
        Material material = materialService.findById(itemDTO.getMaterialId());

        // Crear el nuevo item
        U_ItemListaMateriales nuevoItem = new U_ItemListaMateriales();
        nuevoItem.setListaMateriales(listaMateriales);
        nuevoItem.setMaterial(material);
        nuevoItem.setCantidad(itemDTO.getCantidad());
        nuevoItem.setObservaciones(itemDTO.getObservaciones());
        nuevoItem.setFechaAgregado(LocalDateTime.now());

        // Guardar el item
        U_ItemListaMateriales itemGuardado = itemMaterialService.registrarItemMaterial(nuevoItem);

        // Actualizar la fecha de modificación de la lista
        listaMateriales.actualizarFechaModificacion();
        listaMaterialesService.registrarListaMateriales(listaMateriales);

        // Crear DTO de respuesta para evitar problemas de serialización
        return new U_ItemListaMaterialesResponseDTO(
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
    public List<U_ItemListaMaterialesResponseDTO> getItemsPorLista(@PathVariable Long listaId){
        U_ListaMateriales listaMateriales = listaMaterialesService.findById(listaId);
        if(listaMateriales == null){
            throw new RuntimeException("Lista de materiales no encontrada con ID: " + listaId);
        }
        List<U_ItemListaMateriales> items = itemMaterialService.findByListaMateriales(listaMateriales);
        
        // Convertir a DTOs para evitar problemas de serialización
        return items.stream()
            .map(item -> new U_ItemListaMaterialesResponseDTO(
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

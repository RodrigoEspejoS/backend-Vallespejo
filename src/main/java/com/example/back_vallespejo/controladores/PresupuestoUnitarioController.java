package com.example.back_vallespejo.controladores;
import com.example.back_vallespejo.models.entities.U_Item_EquipoyHerramientas;
import com.example.back_vallespejo.models.dto.ItemPresupuestoDTO;
import com.example.back_vallespejo.models.dto.ObtenerTotalesPresupuestoUnitarioDTO;
import com.example.back_vallespejo.models.dto.PresupuestoUnitarioDTO;
import com.example.back_vallespejo.models.dto.AddItemMaterialRequest;
import com.example.back_vallespejo.models.dto.AddItemCatalogRequest;
import com.example.back_vallespejo.models.dto.UpdatePresupuestoUnitarioRequest;
import com.example.back_vallespejo.models.entities.Material;
import com.example.back_vallespejo.models.entities.TD_Presupuestos;
import com.example.back_vallespejo.models.dao.IItemMaterialDAO;
import com.example.back_vallespejo.models.dao.IMaterialDAO;
import com.example.back_vallespejo.models.dao.ITDPresupuestosDAO;
import com.example.back_vallespejo.models.dao.IUItemEquipoyHerramientasDAO;
import com.example.back_vallespejo.models.entities.ItemMaterial;
import com.example.back_vallespejo.models.entities.U_Item_ManodeObra;
import com.example.back_vallespejo.models.dao.IUItemManodeObraDAO;

import com.example.back_vallespejo.models.entities.ListaMateriales;
import com.example.back_vallespejo.models.entities.Presupuesto_unitario;
import com.example.back_vallespejo.models.entities.U_EquipoyHerramientas;
import com.example.back_vallespejo.models.entities.U_ManodeObra;
import com.example.back_vallespejo.service.IPresupuestoUnitarioService;
// import com.example.back_vallespejo.service.PresupuestoUnitarioDTOAssembler; // Reemplazado por método de servicio

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class PresupuestoUnitarioController {
    @Autowired
    private IUItemEquipoyHerramientasDAO itemEquiposDAO;
    @Autowired
    private IItemMaterialDAO itemMaterialDAO;
    @Autowired
    private IPresupuestoUnitarioService presupuestoUnitarioService;
    @Autowired
    private IUItemManodeObraDAO manoObraDAO;
    @Autowired
    private ITDPresupuestosDAO tdPresupuestosDAO;
    @Autowired
    private IMaterialDAO materialDAO;

    @PostMapping("/presupuesto-unitario/{id}/manodeobra")
    @ResponseStatus(HttpStatus.CREATED)
    public String agregarItemManodeObra(@PathVariable Long id, @RequestBody AddItemCatalogRequest dto) {

        Presupuesto_unitario presupuesto = presupuestoUnitarioService.findById(id);

        // control de errores para evitar nulos
        if (presupuesto == null) throw new RuntimeException("Presupuesto unitario no encontrado");
        if (dto.getTdPresupuestoId()==null) throw new RuntimeException("tdPresupuestoId requerido");

        //td_presupuestos es la base de datos para crear equipos y mano de obra
        TD_Presupuestos cat = tdPresupuestosDAO.findById(dto.getTdPresupuestoId()) // hace un findById del id que se acaba de colocar en el post de json
                .orElseThrow(() -> new RuntimeException("TD_Presupuestos no encontrado"));//control de errores

        U_ManodeObra lista = presupuesto.getUManodeObra(); //UManodeObra es la lista de mano de obra que esta dentro de presupuesto unitario
        if (lista == null) throw new RuntimeException("Lista de mano de obra no inicializada en el presupuesto");// control de errores, pero al crear una actividad automaticamente se crean las 3 listas correspondientes

        U_Item_ManodeObra item = new U_Item_ManodeObra(); // crea un nuevo item de mano de obra
        item.setListaManodeObra(lista); // lo asigna a la lista de mano de obra del presupuesto que ya se ingresó su id
        item.setCodigo(cat.getCodigo()); // los cat.get... lo que hace es copiar datos del td_presupuesto que ya asignaste su id
        item.setDesc_recurso(cat.getDesc_recurso());
        item.setUnidad(cat.getUnidad());
        item.setCuadrilla(cat.getCuadrilla());
        item.setPrecio_unitario(dto.getPrecioOverride()!=null? dto.getPrecioOverride(): cat.getPrecioUnitario()); // condicional de operador ternario para verificar si colocaste un precio nuevo en el post, sino toma el valor de la tabla de datos
        item.setCantidad(dto.getCantidad()!=null? dto.getCantidad():1.0);
        item.setTdPresupuesto(cat);
        if (lista.getItemManodeObra() == null) lista.setItemManodeObra(new java.util.ArrayList<>());
        lista.getItemManodeObra().add(item);
    presupuestoUnitarioService.registrar(presupuesto);
    return "Agregado correctamente";
    }

    @PostMapping("/presupuesto-unitario/{id}/equipos")
    @ResponseStatus(HttpStatus.CREATED)
    public String agregarItemEquipo(@PathVariable Long id, @RequestBody AddItemCatalogRequest dto) {
        Presupuesto_unitario presupuesto = presupuestoUnitarioService.findById(id);
        if (presupuesto == null) throw new RuntimeException("Presupuesto unitario no encontrado");
        U_EquipoyHerramientas lista = presupuesto.getUEquipoyHerramientas();
        if (lista == null) throw new RuntimeException("Lista de equipos no inicializada en el presupuesto");
        if (dto.getTdPresupuestoId()==null) throw new RuntimeException("tdPresupuestoId requerido");
        TD_Presupuestos cat = tdPresupuestosDAO.findById(dto.getTdPresupuestoId())
                .orElseThrow(() -> new RuntimeException("TD_Presupuestos no encontrado"));
        U_Item_EquipoyHerramientas item = new U_Item_EquipoyHerramientas();
        item.setUEquipoyHerramientas(lista);
        item.setCodigo(cat.getCodigo());
        item.setDesc_recurso(cat.getDesc_recurso());
        item.setUnidad(cat.getUnidad());
        item.setCuadrilla(cat.getCuadrilla());
        item.setPrecio_unitario(dto.getPrecioOverride()!=null? dto.getPrecioOverride(): cat.getPrecioUnitario());
        item.setCantidad(dto.getCantidad()!=null? dto.getCantidad():1.0);
        item.setTdPresupuesto(cat);
        if (lista.getUItemEquipoyHerramientas() == null) lista.setUItemEquipoyHerramientas(new java.util.ArrayList<>());
        lista.getUItemEquipoyHerramientas().add(item);
    presupuestoUnitarioService.registrar(presupuesto);
    return "Agregado correctamente";
    }

    @PostMapping("/presupuesto-unitario/{id}/materiales")
    @ResponseStatus(HttpStatus.CREATED)
    public String agregarItemMaterial(@PathVariable Long id, @RequestBody AddItemMaterialRequest req) {
        
        Presupuesto_unitario presupuesto = presupuestoUnitarioService.findById(id);
        
        // control de errores para evitar nulos
        if (presupuesto == null) throw new RuntimeException("Presupuesto unitario no encontrado");
        if (req.getMaterialId() == null) throw new RuntimeException("materialId requerido");
        
        // Material es la base de datos para crear materiales
        Material material = materialDAO.findById(req.getMaterialId())
                .orElseThrow(() -> new RuntimeException("Material no encontrado"));
        
        ListaMateriales lista = presupuesto.getListaMateriales(); // ListaMateriales es la lista de materiales que esta dentro de presupuesto unitario
        if (lista == null) throw new RuntimeException("Lista de materiales no inicializada en el presupuesto"); // control de errores, pero al crear una actividad automaticamente se crean las 3 listas correspondientes
        
        ItemMaterial item = new ItemMaterial(); // crea un nuevo item de material
        item.setListaMateriales(lista); // lo asigna a la lista de materiales del presupuesto que ya se ingresó su id
        item.setMaterial(material);
        item.setCantidad(req.getCantidad() != null ? req.getCantidad() : 1);
        // sincroniza precio y subtotal internamente
        
        if (lista.getItems() == null) lista.setItems(new java.util.ArrayList<>());
        lista.getItems().add(item);
        presupuestoUnitarioService.registrar(presupuesto);
        return "Agregado correctamente";
    }

        @PutMapping("/presupuesto-unitario/equipos/{itemId}/cantidad")
    public Map<String,Object> actualizarCantidadEquipo(@PathVariable Long itemId, @RequestBody Double nuevaCantidad) {
        U_Item_EquipoyHerramientas item = itemEquiposDAO.findById(itemId)
            .orElseThrow(() -> new RuntimeException("ItemEquipo no encontrado"));
        item.setCantidad(nuevaCantidad);
        itemEquiposDAO.save(item);
        Map<String,Object> resp = new HashMap<>();
        resp.put("status", "OK");
        resp.put("itemId", item.getId());
        resp.put("nuevaCantidad", item.getCantidad());
        return resp;
    }


    @PutMapping("/presupuesto-unitario/mano-obra/{itemId}/cantidad")
    public Map<String,Object> actualizarCantidadManoObra(@PathVariable Long itemId, @RequestBody Double nuevaCantidad) {
        U_Item_ManodeObra item = manoObraDAO.findById(itemId)
            .orElseThrow(() -> new RuntimeException("ItemManodeObra no encontrado"));
        item.setCantidad(nuevaCantidad);
        manoObraDAO.save(item);
        Map<String,Object> resp = new HashMap<>();
        resp.put("status", "OK");
        resp.put("itemId", item.getId());
        resp.put("nuevaCantidad", item.getCantidad());
        return resp;
    }

    @PutMapping("/presupuesto-unitario/materiales/{itemId}/cantidad")
    public Map<String,Object> actualizarCantidadMaterial(@PathVariable Long itemId, @RequestBody Integer nuevaCantidad) {
        ItemMaterial item = itemMaterialDAO.findById(itemId)
            .orElseThrow(() -> new RuntimeException("ItemMaterial no encontrado"));
        item.setCantidad(nuevaCantidad);
        itemMaterialDAO.save(item);
        Map<String,Object> resp = new HashMap<>();
        resp.put("status", "OK");
        resp.put("itemId", item.getId());
        resp.put("nuevaCantidad", item.getCantidad());
        return resp;
    }



    @GetMapping("/presupuesto-unitario/{id}")
    public PresupuestoUnitarioDTO obtenerPorId(@PathVariable Long id) {
        PresupuestoUnitarioDTO dto = presupuestoUnitarioService.obtenerDTO(id);
        if (dto == null) throw new RuntimeException("Presupuesto unitario no encontrado");
        return dto;
    }

    @PutMapping("/presupuesto-unitario/{id}/rendimiento")
    public ResponseEntity<String> editarRendimientoPresupuesto(@PathVariable Long id, @RequestBody UpdatePresupuestoUnitarioRequest request) {
        
        Presupuesto_unitario presupuesto = presupuestoUnitarioService.findById(id);
        
        // control de errores para evitar nulos
        if (presupuesto == null) {
            return ResponseEntity.badRequest().body("Presupuesto unitario no encontrado");
        }
        
        // Actualizar solo los campos especificados
        if (request.getUnidad() != null) {
            presupuesto.setUnidad(request.getUnidad());
        }
        if (request.getU_rendimiento() != null) {
            presupuesto.setU_rendimiento(request.getU_rendimiento());
        }
        if (request.getT_rendimiento() != null) {
            presupuesto.setT_rendimiento(request.getT_rendimiento());
        }
        
        // Guardar cambios
        presupuestoUnitarioService.registrar(presupuesto);
        return ResponseEntity.ok("Rendimiento actualizado correctamente");
    }

    // Endpoint que sirve para obtener datos totales de cada lista 
    @GetMapping("/presupuesto-unitario/{id}/totales")
    public ObtenerTotalesPresupuestoUnitarioDTO obtenerTotales(@PathVariable Long id) {
        Presupuesto_unitario entity = presupuestoUnitarioService.findById(id);
        if (entity == null) throw new RuntimeException("Presupuesto unitario no encontrado");
        Double subTotalMateriales = (entity.getListaMateriales() != null) ? entity.getListaMateriales().getTotalEstimado() : 0.0;
        Double subTotalEquipos = (entity.getUEquipoyHerramientas() != null) ? entity.getUEquipoyHerramientas().getSubtotal() : 0.0;
        Double subTotalManoObra = (entity.getUManodeObra() != null) ? entity.getUManodeObra().getSubtotal() : 0.0;
        Double total = subTotalMateriales + subTotalEquipos + subTotalManoObra;
        ObtenerTotalesPresupuestoUnitarioDTO dto = new ObtenerTotalesPresupuestoUnitarioDTO();
        dto.setSubTotalMateriales(subTotalMateriales);
        dto.setSubTotalEquipos(subTotalEquipos);
        dto.setSubTotalManoObra(subTotalManoObra);
        dto.setTotal(total);
        return dto;
    }

    @GetMapping("/presupuesto-unitario/{id}/items")
    public List<ItemPresupuestoDTO> obtenerItemsPresupuesto(@PathVariable Long id) {
        return presupuestoUnitarioService.obtenerItems(id);
    }

    @GetMapping("/presupuesto-unitario/{id}/items/equipos")
    public List<ItemPresupuestoDTO> obtenerItemsEquipos(@PathVariable Long id) {
        return presupuestoUnitarioService.obtenerItemsEquipos(id);
    }

    @GetMapping("/presupuesto-unitario/{id}/items/materiales")
    public List<ItemPresupuestoDTO> obtenerItemsMateriales(@PathVariable Long id) {
        return presupuestoUnitarioService.obtenerItemsMateriales(id);
    }

    @GetMapping("/presupuesto-unitario/{id}/items/mano-obra")
    public List<ItemPresupuestoDTO> obtenerItemsManoObra(@PathVariable Long id) {
        return presupuestoUnitarioService.obtenerItemsManoObra(id);
    }
}

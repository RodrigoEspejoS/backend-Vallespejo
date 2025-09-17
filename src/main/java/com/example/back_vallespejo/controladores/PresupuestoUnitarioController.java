package com.example.back_vallespejo.controladores;
import com.example.back_vallespejo.models.entities.U_Item_EquipoyHerramientas;
import com.example.back_vallespejo.models.dto.ItemCatalogoResponseDTO;
import com.example.back_vallespejo.models.dto.AddItemMaterialRequest;
import com.example.back_vallespejo.models.dto.AddItemCatalogRequest;
import com.example.back_vallespejo.models.entities.Material;
import com.example.back_vallespejo.models.entities.TD_Presupuestos;
import com.example.back_vallespejo.models.dao.IMaterialDAO;
import com.example.back_vallespejo.models.dao.ITDPresupuestosDAO;
import com.example.back_vallespejo.models.entities.ItemMaterial;
import com.example.back_vallespejo.models.entities.U_Item_ManodeObra;
import com.example.back_vallespejo.models.dao.IUItemManodeObraDAO;

import com.example.back_vallespejo.models.entities.ListaMateriales;
import com.example.back_vallespejo.models.entities.Presupuesto_unitario;
import com.example.back_vallespejo.models.entities.U_EquipoyHerramientas;
import com.example.back_vallespejo.models.entities.U_ManodeObra;
import com.example.back_vallespejo.service.IPresupuestoUnitarioService;
// import com.example.back_vallespejo.service.PresupuestoUnitarioDTOAssembler; // Reemplazado por método de servicio

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class PresupuestoUnitarioController {
    @PostMapping("/presupuesto-unitario/{id}/equipos")
    @ResponseStatus(HttpStatus.CREATED)
    public java.util.Map<String,Object> agregarItemEquipo(@PathVariable Long id, @RequestBody AddItemCatalogRequest dto) {
        Presupuesto_unitario presupuesto = presupuestoUnitarioService.findById(id);
        if (presupuesto == null) throw new RuntimeException("Presupuesto unitario no encontrado");
        U_EquipoyHerramientas lista = presupuesto.getUEquipoyHerramientas();
        if (lista == null) throw new RuntimeException("Lista de equipos no inicializada en el presupuesto");
        if (dto.getTdPresupuestoId()==null) throw new RuntimeException("tdPresupuestoId requerido");
        TD_Presupuestos cat = tdPresupuestosDAO.findById(dto.getTdPresupuestoId())
                .orElseThrow(() -> new RuntimeException("TD_Presupuestos no encontrado"));
        U_Item_EquipoyHerramientas item = new U_Item_EquipoyHerramientas();
        item.setUEquipoyHerramientas(lista);
        // Copia snapshot desde catálogo
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
    java.util.Map<String,Object> resp = new java.util.HashMap<>();
    resp.put("status","OK");
    resp.put("presupuestoId", presupuesto.getId());
    resp.put("itemId", item.getId());
    resp.put("tipo","EQUIPO");
    resp.put("cantidad", item.getCantidad());
    resp.put("precioUnitario", item.getPrecio_unitario());
    resp.put("subtotal", item.getSubTotal_precio_unitario());
    return resp;
    }

    @PostMapping("/presupuesto-unitario/{id}/materiales")
    @ResponseStatus(HttpStatus.CREATED)
    public java.util.Map<String,Object> agregarItemMaterial(@PathVariable Long id, @RequestBody AddItemMaterialRequest req) {
        Presupuesto_unitario presupuesto = presupuestoUnitarioService.findById(id);
        if (presupuesto == null) throw new RuntimeException("Presupuesto unitario no encontrado");
        if (req.getMaterialId()==null) throw new RuntimeException("materialId requerido");
        Material material = materialDAO.findById(req.getMaterialId())
                .orElseThrow(() -> new RuntimeException("Material no encontrado"));
        ListaMateriales lista = presupuesto.getListaMateriales();
        ItemMaterial item = new ItemMaterial();
        item.setListaMateriales(lista);
        item.setMaterial(material);
        // Snapshot campos
        item.setCantidad(req.getCantidad()!=null? req.getCantidad():1);
        // sincroniza precio y subtotal internamente
        if (lista.getItems() == null) lista.setItems(new java.util.ArrayList<>());
        lista.getItems().add(item);
    presupuestoUnitarioService.registrar(presupuesto);
    java.util.Map<String,Object> resp = new java.util.HashMap<>();
    resp.put("status","OK");
    resp.put("presupuestoId", presupuesto.getId());
    resp.put("itemId", item.getId());
    resp.put("tipo","MATERIAL");
    resp.put("cantidad", item.getCantidad());
    resp.put("precioUnitario", item.getPrecioUnitarioRecurso());
    resp.put("subtotal", item.getSubtotal_PrecioUnitario());
    return resp;
    }

    @PostMapping("/presupuesto-unitario/{id}/manodeobra")
    @ResponseStatus(HttpStatus.CREATED)
    public java.util.Map<String,Object> agregarItemManodeObra(@PathVariable Long id, @RequestBody AddItemCatalogRequest dto) {
        Presupuesto_unitario presupuesto = presupuestoUnitarioService.findById(id);
        if (presupuesto == null) throw new RuntimeException("Presupuesto unitario no encontrado");
        if (dto.getTdPresupuestoId()==null) throw new RuntimeException("tdPresupuestoId requerido");
        TD_Presupuestos cat = tdPresupuestosDAO.findById(dto.getTdPresupuestoId())
                .orElseThrow(() -> new RuntimeException("TD_Presupuestos no encontrado"));
        U_ManodeObra lista = presupuesto.getUManodeObra();
        if (lista == null) throw new RuntimeException("Lista de mano de obra no inicializada en el presupuesto");
        U_Item_ManodeObra item = new U_Item_ManodeObra();
        item.setListaManodeObra(lista);
        item.setCodigo(cat.getCodigo());
        item.setDesc_recurso(cat.getDesc_recurso());
        item.setUnidad(cat.getUnidad());
        item.setCuadrilla(cat.getCuadrilla());
        item.setPrecio_unitario(dto.getPrecioOverride()!=null? dto.getPrecioOverride(): cat.getPrecioUnitario());
        item.setCantidad(dto.getCantidad()!=null? dto.getCantidad():1.0);
        item.setTdPresupuesto(cat);
        if (lista.getItemManodeObra() == null) lista.setItemManodeObra(new java.util.ArrayList<>());
        lista.getItemManodeObra().add(item);
    presupuestoUnitarioService.registrar(presupuesto);
    java.util.Map<String,Object> resp = new java.util.HashMap<>();
    resp.put("status","OK");
    resp.put("presupuestoId", presupuesto.getId());
    resp.put("itemId", item.getId());
    resp.put("tipo","MANO_OBRA");
    resp.put("cantidad", item.getCantidad());
    resp.put("precioUnitario", item.getPrecio_unitario());
    resp.put("subtotal", item.getSubtotal());
    return resp;
    }

    @Autowired
    private IPresupuestoUnitarioService presupuestoUnitarioService;
    @Autowired
    private IUItemManodeObraDAO manoObraDAO;
    @Autowired
    private ITDPresupuestosDAO tdPresupuestosDAO;
    @Autowired
    private IMaterialDAO materialDAO;

    @GetMapping("/presupuesto-unitario/{id}")
    public java.util.Map<String,Object> obtenerPorId(@PathVariable Long id) {
        Presupuesto_unitario presupuesto = presupuestoUnitarioService.findById(id);
        if (presupuesto == null) throw new RuntimeException("Presupuesto unitario no encontrado");
        java.util.Map<String,Object> respuesta = new java.util.HashMap<>();
        respuesta.put("id", presupuesto.getId());
    respuesta.put("descripcion", presupuesto.getDescripcion());
        // Materiales
        java.util.List<ItemCatalogoResponseDTO> materiales = new java.util.ArrayList<>();
        if (presupuesto.getListaMateriales()!=null && presupuesto.getListaMateriales().getItems()!=null){
            for (ItemMaterial im: presupuesto.getListaMateriales().getItems()){
                ItemCatalogoResponseDTO r = new ItemCatalogoResponseDTO();
                r.setId(im.getId());
                r.setTipo("MATERIAL");
                if (im.getMaterial()!=null){
                    r.setCatalogoId(im.getMaterial().getId());
                    r.setCatalogoCodigo(im.getMaterial().getSerie());
                    r.setCatalogoDescripcion(im.getMaterial().getNombre());
                    r.setCatalogoPrecioUnitario(im.getMaterial().getCostoUnitario());
                }
                r.setCodigo(im.getCodigo());
                r.setDescripcion(im.getDesc_recurso());
                r.setUnidad(im.getUnidad());
                r.setCantidad(im.getCantidad()!=null? im.getCantidad().doubleValue(): null);
                r.setPrecioUnitario(im.getPrecioUnitarioRecurso());
                r.setSubtotal(im.getSubtotal_PrecioUnitario());
                materiales.add(r);
            }
        }
        respuesta.put("materiales", materiales);
        // Equipos
        java.util.List<ItemCatalogoResponseDTO> equipos = new java.util.ArrayList<>();
        if (presupuesto.getUEquipoyHerramientas()!=null && presupuesto.getUEquipoyHerramientas().getUItemEquipoyHerramientas()!=null){
            for (U_Item_EquipoyHerramientas ie: presupuesto.getUEquipoyHerramientas().getUItemEquipoyHerramientas()){
                ItemCatalogoResponseDTO r = new ItemCatalogoResponseDTO();
                r.setId(ie.getId());
                r.setTipo("EQUIPO");
                if (ie.getTdPresupuesto()!=null){
                    r.setCatalogoId(ie.getTdPresupuesto().getId());
                    r.setCatalogoCodigo(ie.getTdPresupuesto().getCodigo());
                    r.setCatalogoDescripcion(ie.getTdPresupuesto().getDesc_recurso());
                    r.setCatalogoPrecioUnitario(ie.getTdPresupuesto().getPrecioUnitario());
                }
                r.setCodigo(ie.getCodigo());
                r.setDescripcion(ie.getDesc_recurso());
                r.setUnidad(ie.getUnidad());
                r.setCuadrilla(ie.getCuadrilla());
                r.setCantidad(ie.getCantidad());
                r.setPrecioUnitario(ie.getPrecio_unitario());
                r.setSubtotal(ie.getSubTotal_precio_unitario());
                equipos.add(r);
            }
        }
        respuesta.put("equipos", equipos);
        // Mano de obra
        java.util.List<ItemCatalogoResponseDTO> manoObra = new java.util.ArrayList<>();
        if (presupuesto.getUManodeObra()!=null && presupuesto.getUManodeObra().getItemManodeObra()!=null){
            for (U_Item_ManodeObra mo: presupuesto.getUManodeObra().getItemManodeObra()){
                ItemCatalogoResponseDTO r = new ItemCatalogoResponseDTO();
                r.setId(mo.getId());
                r.setTipo("MANO_OBRA");
                if (mo.getTdPresupuesto()!=null){
                    r.setCatalogoId(mo.getTdPresupuesto().getId());
                    r.setCatalogoCodigo(mo.getTdPresupuesto().getCodigo());
                    r.setCatalogoDescripcion(mo.getTdPresupuesto().getDesc_recurso());
                    r.setCatalogoPrecioUnitario(mo.getTdPresupuesto().getPrecioUnitario());
                }
                r.setCodigo(mo.getCodigo());
                r.setDescripcion(mo.getDesc_recurso());
                r.setUnidad(mo.getUnidad());
                r.setCuadrilla(mo.getCuadrilla());
                r.setCantidad(mo.getCantidad());
                r.setPrecioUnitario(mo.getPrecio_unitario());
                r.setSubtotal(mo.getSubtotal());
                manoObra.add(r);
            }
        }
        respuesta.put("manoObra", manoObra);
        respuesta.put("total", presupuesto.getTotal_presupuesto_unitario());
        return respuesta;
    }

    // Endpoint alternativo que retorna solo totales (útil si no se requiere detalle)
    @GetMapping("/presupuesto-unitario/{id}/totales")
    public java.util.Map<String, Double> obtenerTotales(@PathVariable Long id) {
        Presupuesto_unitario entity = presupuestoUnitarioService.findById(id);
        if (entity == null) throw new RuntimeException("Presupuesto unitario no encontrado");
        double total = entity.getTotal_presupuesto_unitario();
        double subtotalEquipos = (entity.getUEquipoyHerramientas()!=null)? entity.getUEquipoyHerramientas().getSubtotal():0.0;
        double subtotalMateriales = (entity.getListaMateriales()!=null)? entity.getListaMateriales().getTotalEstimado():0.0;
        double subtotalManoObra = (entity.getUManodeObra()!=null)? entity.getUManodeObra().getSubtotal():0.0;
        java.util.Map<String, Double> response = new java.util.HashMap<>();
        response.put("subtotalEquiposHerramientas", subtotalEquipos);
        response.put("subtotalMateriales", subtotalMateriales);
        response.put("subtotalManoObra", subtotalManoObra);
        response.put("totalPresupuestoUnitario", total);
        return response;
    }

    @GetMapping("/presupuesto-unitario/{id}/items")
    public java.util.List<com.example.back_vallespejo.models.dto.ItemPresupuestoDTO> obtenerItemsPresupuesto(@PathVariable Long id) {
        return presupuestoUnitarioService.obtenerItems(id);
    }

    @GetMapping("/presupuesto-unitario/{id}/items/equipos")
    public java.util.List<com.example.back_vallespejo.models.dto.ItemPresupuestoDTO> obtenerItemsEquipos(@PathVariable Long id) {
        return presupuestoUnitarioService.obtenerItemsEquipos(id);
    }

    @GetMapping("/presupuesto-unitario/{id}/items/materiales")
    public java.util.List<com.example.back_vallespejo.models.dto.ItemPresupuestoDTO> obtenerItemsMateriales(@PathVariable Long id) {
        return presupuestoUnitarioService.obtenerItemsMateriales(id);
    }

    @GetMapping("/presupuesto-unitario/{id}/items/mano-obra")
    public java.util.List<com.example.back_vallespejo.models.dto.ItemPresupuestoDTO> obtenerItemsManoObra(@PathVariable Long id) {
        return presupuestoUnitarioService.obtenerItemsManoObra(id);
    }
}

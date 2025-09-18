package com.example.back_vallespejo.service;

import com.example.back_vallespejo.models.dao.IPresupuestoUnitarioDAO;
import com.example.back_vallespejo.models.dto.PresupuestoUnitarioDTO;
import com.example.back_vallespejo.models.dto.EquiposHerramientasDTO;
import com.example.back_vallespejo.models.dto.ItemEquiposHerramientasDTO;
import com.example.back_vallespejo.models.dto.ItemManoDeObraDTO;
import com.example.back_vallespejo.models.dto.ItemMaterialDTO;
import com.example.back_vallespejo.models.dto.ItemMaterialResponseDTO;
import com.example.back_vallespejo.models.dto.ItemPresupuestoDTO;
import com.example.back_vallespejo.models.dto.ListaMaterialesDTO;
import com.example.back_vallespejo.models.dto.ManoDeObraDTO;
import com.example.back_vallespejo.models.entities.U_Item_EquipoyHerramientas;
import com.example.back_vallespejo.models.entities.ItemMaterial;
import com.example.back_vallespejo.models.entities.U_Item_ManodeObra;
import com.example.back_vallespejo.models.entities.ListaMateriales;
import com.example.back_vallespejo.models.entities.U_EquipoyHerramientas;
import com.example.back_vallespejo.models.entities.U_ManodeObra;
import com.example.back_vallespejo.models.entities.Presupuesto_unitario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class PresupuestoUnitarioServiceImpl implements IPresupuestoUnitarioService {
    @Autowired
    private IPresupuestoUnitarioDAO dao;

    @Override
    public Presupuesto_unitario registrar(Presupuesto_unitario entity) {
        return dao.save(entity);
    }

    @Override
    public List<Presupuesto_unitario> getAll() {
        List<Presupuesto_unitario> list = new ArrayList<>();
        dao.findAll().forEach(list::add);
        return list;
    }

    @Override
    public Presupuesto_unitario findById(Long id) {
        return dao.findById(id).orElse(null);
    }

    @Override
    public void delete(Presupuesto_unitario entity) {
        dao.delete(entity);
    }

    @Override
    public PresupuestoUnitarioDTO obtenerDTO(Long id) { // Método para obtener el DTO con cálculos de los subtotales y total general de cada p. unitario
        Presupuesto_unitario entity = findById(id);
        if (entity == null) {
            return null;
        }
        PresupuestoUnitarioDTO dto = new PresupuestoUnitarioDTO();
        dto.setId(entity.getId());
        dto.setDescripcion(entity.getDescripcion());
        dto.setUnidad(entity.getUnidad());
        dto.setU_rendimiento(entity.getU_rendimiento());
        dto.setT_rendimiento(entity.getT_rendimiento());

        // Lista de materiales
        ListaMaterialesDTO listaMaterialesDTO = new ListaMaterialesDTO();
        if (entity.getListaMateriales() != null) {
            listaMaterialesDTO.setNombre(entity.getListaMateriales().getNombre());
            listaMaterialesDTO.setDescripcion(entity.getListaMateriales().getDescripcion());
            listaMaterialesDTO.setSubTotal(entity.getListaMateriales().getTotalEstimado());
            java.util.List<ItemMaterialResponseDTO> items = new java.util.ArrayList<>();
            if (entity.getListaMateriales().getItems() != null) {
                for (ItemMaterial it : entity.getListaMateriales().getItems()) {
                    ItemMaterialResponseDTO itemDTO = new ItemMaterialResponseDTO();
                    itemDTO.setId(it.getId());
                    if (it.getMaterial() != null) {
                        itemDTO.setMaterialNombre(it.getMaterial().getNombre());
                        itemDTO.setMaterialSerie(it.getMaterial().getSerie());
                    }
                    itemDTO.setCantidad(it.getCantidad());
                    itemDTO.setPrecioUnitario(it.getPrecioUnitarioRecurso());
                    itemDTO.setSubtotal(it.getSubtotal_PrecioUnitario());
                    if (it.getListaMateriales() != null) {
                        itemDTO.setListaMaterialesId(it.getListaMateriales().getId());
                        itemDTO.setListaMaterialesNombre(it.getListaMateriales().getNombre());
                    }
                    items.add(itemDTO);
                }
            }
            listaMaterialesDTO.setItems(items);
        }
        dto.setListaMateriales(listaMaterialesDTO);

        // Equipos y herramientas
        EquiposHerramientasDTO equiposDTO = new EquiposHerramientasDTO();
        if (entity.getUEquipoyHerramientas() != null) {
            equiposDTO.setId(entity.getUEquipoyHerramientas().getId());
            equiposDTO.setSubtotal(entity.getUEquipoyHerramientas().getSubtotal());
            java.util.List<ItemEquiposHerramientasDTO> items = new java.util.ArrayList<>();
            if (entity.getUEquipoyHerramientas().getUItemEquipoyHerramientas() != null) {
                for (U_Item_EquipoyHerramientas it : entity.getUEquipoyHerramientas().getUItemEquipoyHerramientas()) {
                    ItemEquiposHerramientasDTO itemDTO = new ItemEquiposHerramientasDTO();
                    itemDTO.setId(it.getId());
                    itemDTO.setCodigo(it.getCodigo());
                    itemDTO.setDescripcion(it.getDesc_recurso());
                    itemDTO.setUnidad(it.getUnidad());
                    itemDTO.setCantidad(it.getCantidad());
                    itemDTO.setPrecioUnitario(it.getPrecio_unitario_recurso());
                    itemDTO.setSubtotal(it.getSubTotal_precio_unitario());
                    itemDTO.setCuadrilla(it.getCuadrilla());
                    items.add(itemDTO);
                }
            }
            equiposDTO.setItems(items);
        }
        dto.setEquiposHerramientas(equiposDTO);

        // Mano de obra
        ManoDeObraDTO manoObraDTO = new ManoDeObraDTO();
        if (entity.getUManodeObra() != null) {
            manoObraDTO.setId(entity.getUManodeObra().getId());
            manoObraDTO.setSubtotal(entity.getUManodeObra().getSubtotal());
            java.util.List<ItemManoDeObraDTO> items = new java.util.ArrayList<>();
            if (entity.getUManodeObra().getItemManodeObra() != null) {
                for (U_Item_ManodeObra it : entity.getUManodeObra().getItemManodeObra()) {
                    ItemManoDeObraDTO itemDTO = new ItemManoDeObraDTO();
                    itemDTO.setId(it.getId());
                    itemDTO.setCodigo(it.getCodigo());
                    itemDTO.setDescripcion(it.getDesc_recurso());
                    itemDTO.setUnidad(it.getUnidad());
                    itemDTO.setCantidad(it.getCantidad());
                    itemDTO.setPrecioUnitario(it.getPrecio_unitario());
                    itemDTO.setSubtotal(it.getSubtotal());
                    itemDTO.setCuadrilla(it.getCuadrilla());
                    items.add(itemDTO);
                }
            }
            manoObraDTO.setItems(items);
        }
        dto.setManoDeObra(manoObraDTO);

        // Cálculos de subtotales y total
        double subtotalEquipos = (entity.getUEquipoyHerramientas() != null) ? entity.getUEquipoyHerramientas().getSubtotal() : 0.0;
        double subtotalMateriales = (entity.getListaMateriales() != null) ? entity.getListaMateriales().getTotalEstimado() : 0.0;
        double subtotalManoObra = (entity.getUManodeObra() != null) ? entity.getUManodeObra().getSubtotal() : 0.0;
        double total = subtotalEquipos + subtotalMateriales + subtotalManoObra;
        dto.setTotalPresupuestoUnitario(total);

        return dto;
    }

    @Override
    public List<ItemPresupuestoDTO> obtenerItems(Long id) {
        Presupuesto_unitario entity = findById(id);
        if (entity == null) return Collections.emptyList();
        List<ItemPresupuestoDTO> resultado = new ArrayList<>();

        // Equipos y herramientas
        if (entity.getUEquipoyHerramientas() != null && entity.getUEquipoyHerramientas().getUItemEquipoyHerramientas() != null) {
            for (U_Item_EquipoyHerramientas it : entity.getUEquipoyHerramientas().getUItemEquipoyHerramientas()) {
                ItemPresupuestoDTO dto = new ItemPresupuestoDTO();
                dto.setId(it.getId());
                dto.setTipo("EQUIPO_HERRAMIENTA");
                dto.setCodigo(it.getCodigo());
                dto.setDescripcion(it.getDesc_recurso());
                dto.setUnidad(it.getUnidad());
                dto.setCantidad(it.getCantidad());
                dto.setPrecioUnitario(it.getPrecio_unitario_recurso());
                dto.setCuadrilla(it.getCuadrilla());
                // Recalcular si es necesario
                it.calcularSubtotal();
                dto.setSubtotal(it.getSubTotal_precio_unitario());
                resultado.add(dto);
            }
        }

        // Materiales
        if (entity.getListaMateriales() != null && entity.getListaMateriales().getItems() != null) {
            for (ItemMaterial it : entity.getListaMateriales().getItems()) {
                ItemPresupuestoDTO dto = new ItemPresupuestoDTO();
                dto.setId(it.getId());
                dto.setTipo("MATERIAL");
                dto.setCodigo(it.getCodigo());
                dto.setDescripcion(it.getDesc_recurso());
                dto.setCantidad(it.getCantidad() != null ? it.getCantidad().doubleValue() : 0.0);
                dto.setPrecioUnitario(it.getPrecioUnitarioRecurso());
                it.calcularSubtotal();
                dto.setSubtotal(it.getSubtotal_PrecioUnitario());
                resultado.add(dto);
            }
        }

        // Mano de obra
        if (entity.getUManodeObra() != null && entity.getUManodeObra().getItemManodeObra() != null) {
            for (U_Item_ManodeObra it : entity.getUManodeObra().getItemManodeObra()) {
                ItemPresupuestoDTO dto = new ItemPresupuestoDTO();
                dto.setId(it.getId());
                dto.setTipo("MANO_OBRA");
                dto.setCodigo(it.getCodigo());
                dto.setDescripcion(it.getDesc_recurso());
                dto.setUnidad(it.getUnidad());
                it.calcularSubtotal();
                dto.setCantidad(it.getCantidad());
                dto.setPrecioUnitario(it.getPrecio_unitario());
                dto.setSubtotal(it.getSubtotal());
                dto.setCuadrilla(it.getCuadrilla());
                resultado.add(dto);
            }
        }
        return resultado;
    }

    @Override
    public List<ItemPresupuestoDTO> obtenerItemsEquipos(Long id) {
        Presupuesto_unitario entity = findById(id);
        if (entity == null || entity.getUEquipoyHerramientas()==null) return Collections.emptyList();
        List<ItemPresupuestoDTO> lista = new ArrayList<>();
        if (entity.getUEquipoyHerramientas().getUItemEquipoyHerramientas()!=null) {
            for (U_Item_EquipoyHerramientas it : entity.getUEquipoyHerramientas().getUItemEquipoyHerramientas()) {
                it.calcularSubtotal();
                ItemPresupuestoDTO dto = new ItemPresupuestoDTO();
                dto.setId(it.getId());
                dto.setTipo("EQUIPO_HERRAMIENTA");
                dto.setCodigo(it.getCodigo());
                dto.setDescripcion(it.getDesc_recurso());
                dto.setUnidad(it.getUnidad());
                dto.setCantidad(it.getCantidad());
                dto.setPrecioUnitario(it.getPrecio_unitario_recurso());
                dto.setSubtotal(it.getSubTotal_precio_unitario());
                dto.setCuadrilla(it.getCuadrilla());
                lista.add(dto);
            }
        }
        return lista;
    }

    @Override
    public List<ItemPresupuestoDTO> obtenerItemsMateriales(Long id) {
        Presupuesto_unitario entity = findById(id);
        if (entity == null || entity.getListaMateriales()==null) return Collections.emptyList();
        List<ItemPresupuestoDTO> lista = new ArrayList<>();
        if (entity.getListaMateriales().getItems()!=null) {
            for (ItemMaterial it : entity.getListaMateriales().getItems()) {
                it.calcularSubtotal();
                ItemPresupuestoDTO dto = new ItemPresupuestoDTO();
                dto.setId(it.getId());
                dto.setTipo("MATERIAL");
                dto.setCodigo(it.getCodigo());
                dto.setDescripcion(it.getDesc_recurso());
                dto.setCantidad(it.getCantidad()!=null? it.getCantidad().doubleValue():0.0);
                dto.setPrecioUnitario(it.getPrecioUnitarioRecurso());
                dto.setSubtotal(it.getSubtotal_PrecioUnitario());
                lista.add(dto);
            }
        }
        return lista;
    }

    @Override
    public List<ItemPresupuestoDTO> obtenerItemsManoObra(Long id) {
        Presupuesto_unitario entity = findById(id);
        if (entity == null || entity.getUManodeObra()==null) return Collections.emptyList();
        List<ItemPresupuestoDTO> lista = new ArrayList<>();
        if (entity.getUManodeObra().getItemManodeObra()!=null) {
            for (U_Item_ManodeObra it : entity.getUManodeObra().getItemManodeObra()) {
                it.calcularSubtotal();
                ItemPresupuestoDTO dto = new ItemPresupuestoDTO();
                dto.setId(it.getId());
                dto.setTipo("MANO_OBRA");
                dto.setCodigo(it.getCodigo());
                dto.setDescripcion(it.getDesc_recurso());
                dto.setUnidad(it.getUnidad());
                dto.setCantidad(it.getCantidad());
                dto.setPrecioUnitario(it.getPrecio_unitario());
                dto.setSubtotal(it.getSubtotal());
                dto.setCuadrilla(it.getCuadrilla());
                lista.add(dto);
            }
        }
        return lista;
    }
}

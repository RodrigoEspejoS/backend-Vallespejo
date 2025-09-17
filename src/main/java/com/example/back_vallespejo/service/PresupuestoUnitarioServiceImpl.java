package com.example.back_vallespejo.service;

import com.example.back_vallespejo.models.dao.IPresupuestoUnitarioDAO;
import com.example.back_vallespejo.models.dto.PresupuestoUnitarioDTO;
import com.example.back_vallespejo.models.dto.ItemPresupuestoDTO;
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
    public PresupuestoUnitarioDTO obtenerDTO(Long id) {
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

        // Cálculos de subtotales desde las entidades
        U_EquipoyHerramientas eq = entity.getUEquipoyHerramientas();
        ListaMateriales lm = entity.getListaMateriales();
        U_ManodeObra mo = entity.getUManodeObra();

        double subtotalEquipos = (eq != null) ? eq.getSubtotal() : 0.0;
        double subtotalMateriales = (lm != null) ? lm.getTotalEstimado() : 0.0;
        double subtotalManoObra = (mo != null) ? mo.getSubtotal() : 0.0;
        double total = subtotalEquipos + subtotalMateriales + subtotalManoObra;
        dto.setTotalPresupuestoUnitario(total);
        // Si se desea retornar estructuras completas, podría llamarse a un assembler futuro.
        return dto;
    }

    @Override
    public List<ItemPresupuestoDTO> obtenerItems(Long id) {
        Presupuesto_unitario entity = findById(id);
        if (entity == null) return java.util.Collections.emptyList();
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
                dto.setUnidad(it.getUnidad());
                dto.setCantidad(it.getCantidad() != null ? it.getCantidad().doubleValue() : 0.0);
                dto.setPrecioUnitario(it.getPrecioUnitarioRecurso());
                dto.setCuadrilla(it.getCuadrilla());
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
        if (entity == null || entity.getUEquipoyHerramientas()==null) return java.util.Collections.emptyList();
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
        if (entity == null || entity.getListaMateriales()==null) return java.util.Collections.emptyList();
        List<ItemPresupuestoDTO> lista = new ArrayList<>();
        if (entity.getListaMateriales().getItems()!=null) {
            for (ItemMaterial it : entity.getListaMateriales().getItems()) {
                it.calcularSubtotal();
                ItemPresupuestoDTO dto = new ItemPresupuestoDTO();
                dto.setId(it.getId());
                dto.setTipo("MATERIAL");
                dto.setCodigo(it.getCodigo());
                dto.setDescripcion(it.getDesc_recurso());
                dto.setUnidad(it.getUnidad());
                dto.setCantidad(it.getCantidad()!=null? it.getCantidad().doubleValue():0.0);
                dto.setPrecioUnitario(it.getPrecioUnitarioRecurso());
                dto.setSubtotal(it.getSubtotal_PrecioUnitario());
                dto.setCuadrilla(it.getCuadrilla());
                lista.add(dto);
            }
        }
        return lista;
    }

    @Override
    public List<ItemPresupuestoDTO> obtenerItemsManoObra(Long id) {
        Presupuesto_unitario entity = findById(id);
        if (entity == null || entity.getUManodeObra()==null) return java.util.Collections.emptyList();
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

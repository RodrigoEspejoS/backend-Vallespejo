package com.example.back_vallespejo.service;

import com.example.back_vallespejo.models.entities.Presupuesto_unitario;
import com.example.back_vallespejo.models.dto.PresupuestoUnitarioDTO;
import com.example.back_vallespejo.models.dto.ItemPresupuestoDTO;
import java.util.List;

public interface IPresupuestoUnitarioService {
    Presupuesto_unitario registrar(Presupuesto_unitario entity);
    List<Presupuesto_unitario> getAll();
    Presupuesto_unitario findById(Long id);
    void delete(Presupuesto_unitario entity);
    PresupuestoUnitarioDTO obtenerDTO(Long id);
    java.util.List<ItemPresupuestoDTO> obtenerItems(Long id);
    java.util.List<ItemPresupuestoDTO> obtenerItemsEquipos(Long id);
    java.util.List<ItemPresupuestoDTO> obtenerItemsMateriales(Long id);
    java.util.List<ItemPresupuestoDTO> obtenerItemsManoObra(Long id);
}

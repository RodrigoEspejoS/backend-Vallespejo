package com.example.back_vallespejo.service;

import com.example.back_vallespejo.models.entities.Presupuesto_unitario;
import java.util.List;

public interface IPresupuestoUnitarioService {
    Presupuesto_unitario registrar(Presupuesto_unitario entity);
    List<Presupuesto_unitario> getAll();
    Presupuesto_unitario findById(Long id);
    void delete(Presupuesto_unitario entity);
}

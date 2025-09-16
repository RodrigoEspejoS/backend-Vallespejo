package com.example.back_vallespejo.service;

import com.example.back_vallespejo.models.entities.TD_Presupuestos;
import java.util.List;

public interface ITDPresupuestosService {
    TD_Presupuestos registrar(TD_Presupuestos entity);
    List<TD_Presupuestos> getAll();
    TD_Presupuestos findById(Long id);
    void delete(TD_Presupuestos entity);
}

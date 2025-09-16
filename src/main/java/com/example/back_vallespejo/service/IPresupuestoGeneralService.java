package com.example.back_vallespejo.service;

import com.example.back_vallespejo.models.entities.Presupuesto_General;
import java.util.List;

public interface IPresupuestoGeneralService {
    Presupuesto_General registrar(Presupuesto_General entity);
    List<Presupuesto_General> getAll();
    Presupuesto_General findById(Long id);
    void delete(Presupuesto_General entity);
}

package com.example.back_vallespejo.service;

import com.example.back_vallespejo.models.entities.Actividades;
import java.util.List;

public interface IActividadesService {
    Actividades registrar(Actividades entity);
    List<Actividades> getAll();
    Actividades findById(Long id);
    void delete(Actividades entity);
}

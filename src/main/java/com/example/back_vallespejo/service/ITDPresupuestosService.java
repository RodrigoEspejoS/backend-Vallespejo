package com.example.back_vallespejo.service;

import com.example.back_vallespejo.models.entities.TD_Presupuestos;
import java.util.List;
import java.util.Optional;

public interface ITDPresupuestosService {
    TD_Presupuestos crear(TD_Presupuestos entity);
    TD_Presupuestos actualizar(Long id, TD_Presupuestos entity);
    Optional<TD_Presupuestos> obtener(Long id);
    Optional<TD_Presupuestos> obtenerPorCodigo(String codigo);
    List<TD_Presupuestos> listar();
    void eliminar(Long id);
}

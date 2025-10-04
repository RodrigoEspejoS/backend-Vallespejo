package com.example.back_vallespejo.models.dao;

import com.example.back_vallespejo.models.entities.Actividades;
import com.example.back_vallespejo.models.entities.Presupuesto_unitario;
import org.springframework.data.repository.CrudRepository;

public interface IActividadesDAO extends CrudRepository<Actividades, Long> {
    Actividades findByPresupuestoUnitario(Presupuesto_unitario presupuestoUnitario);
}

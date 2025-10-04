package com.example.back_vallespejo.models.dao;

import com.example.back_vallespejo.models.entities.U_Item_EquipoyHerramientas;
import com.example.back_vallespejo.models.entities.TD_Presupuestos;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface IUItemEquipoyHerramientasDAO extends CrudRepository<U_Item_EquipoyHerramientas, Long> {
    List<U_Item_EquipoyHerramientas> findByTdPresupuesto(TD_Presupuestos tdPresupuesto);
}

package com.example.back_vallespejo.models.dao;

import com.example.back_vallespejo.models.entities.U_Item_ManodeObra;
import com.example.back_vallespejo.models.entities.TD_Presupuestos;
import org.springframework.data.repository.CrudRepository;
import java.util.Optional;
import java.util.List;

public interface IUItemManodeObraDAO extends CrudRepository<U_Item_ManodeObra, Long> {
	Optional<U_Item_ManodeObra> findByCodigo(String codigo);
	List<U_Item_ManodeObra> findByTdPresupuesto(TD_Presupuestos tdPresupuesto);
}

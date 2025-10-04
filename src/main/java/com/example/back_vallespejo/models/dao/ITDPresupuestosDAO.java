package com.example.back_vallespejo.models.dao;

import com.example.back_vallespejo.models.entities.TD_Presupuestos;
import org.springframework.data.repository.CrudRepository;
import java.util.Optional;

public interface ITDPresupuestosDAO extends CrudRepository<TD_Presupuestos, Long> {
	Optional<TD_Presupuestos> findByCodigo(String codigo);
}

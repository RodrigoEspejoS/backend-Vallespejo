package com.example.back_vallespejo.models.dao;

import com.example.back_vallespejo.models.entities.Proveedor;
import org.springframework.data.repository.CrudRepository;

public interface IProveedorDAO extends CrudRepository<Proveedor, Long> {
}

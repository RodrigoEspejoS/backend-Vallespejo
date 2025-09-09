package com.example.back_vallespejo.models.DAO;

import com.example.back_vallespejo.models.entities.Proveedor;
import org.springframework.data.repository.CrudRepository;

public interface IProveedorDAO extends CrudRepository<Proveedor, Long> {
}

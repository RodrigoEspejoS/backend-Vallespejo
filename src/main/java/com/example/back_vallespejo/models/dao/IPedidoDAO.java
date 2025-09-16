package com.example.back_vallespejo.models.dao;

import com.example.back_vallespejo.models.entities.Pedido;
import org.springframework.data.repository.CrudRepository;

public interface IPedidoDAO extends CrudRepository<Pedido, Long> {
}

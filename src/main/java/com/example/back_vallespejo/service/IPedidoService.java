package com.example.back_vallespejo.service;

import com.example.back_vallespejo.models.entities.Pedido;
import java.util.List;

public interface IPedidoService {
    Pedido registrar(Pedido entity);
    List<Pedido> getAll();
    Pedido findById(Long id);
    void delete(Pedido entity);
}

package com.example.back_vallespejo.service;

import com.example.back_vallespejo.models.dao.IPedidoDAO;
import com.example.back_vallespejo.models.entities.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class PedidoServiceImpl implements IPedidoService {
    @Autowired
    private IPedidoDAO dao;

    @Override
    public Pedido registrar(Pedido entity) {
        return dao.save(entity);
    }

    @Override
    public List<Pedido> getAll() {
        List<Pedido> list = new ArrayList<>();
        dao.findAll().forEach(list::add);
        return list;
    }

    @Override
    public Pedido findById(Long id) {
        return dao.findById(id).orElse(null);
    }

    @Override
    public void delete(Pedido entity) {
        dao.delete(entity);
    }
}

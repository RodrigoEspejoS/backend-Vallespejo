package com.example.back_vallespejo.service;

import com.example.back_vallespejo.models.dao.IPresupuestoUnitarioDAO;
import com.example.back_vallespejo.models.entities.Presupuesto_unitario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class PresupuestoUnitarioServiceImpl implements IPresupuestoUnitarioService {
    @Autowired
    private IPresupuestoUnitarioDAO dao;

    @Override
    public Presupuesto_unitario registrar(Presupuesto_unitario entity) {
        return dao.save(entity);
    }

    @Override
    public List<Presupuesto_unitario> getAll() {
        List<Presupuesto_unitario> list = new ArrayList<>();
        dao.findAll().forEach(list::add);
        return list;
    }

    @Override
    public Presupuesto_unitario findById(Long id) {
        return dao.findById(id).orElse(null);
    }

    @Override
    public void delete(Presupuesto_unitario entity) {
        dao.delete(entity);
    }
}

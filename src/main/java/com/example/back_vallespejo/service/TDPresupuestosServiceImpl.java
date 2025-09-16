package com.example.back_vallespejo.service;

import com.example.back_vallespejo.models.dao.ITDPresupuestosDAO;
import com.example.back_vallespejo.models.entities.TD_Presupuestos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class TDPresupuestosServiceImpl implements ITDPresupuestosService {
    @Autowired
    private ITDPresupuestosDAO dao;

    @Override
    public TD_Presupuestos registrar(TD_Presupuestos entity) {
        return dao.save(entity);
    }

    @Override
    public List<TD_Presupuestos> getAll() {
        List<TD_Presupuestos> list = new ArrayList<>();
        dao.findAll().forEach(list::add);
        return list;
    }

    @Override
    public TD_Presupuestos findById(Long id) {
        return dao.findById(id).orElse(null);
    }

    @Override
    public void delete(TD_Presupuestos entity) {
        dao.delete(entity);
    }
}

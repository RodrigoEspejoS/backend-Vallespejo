package com.example.back_vallespejo.service;

import com.example.back_vallespejo.models.dao.IPresupuestoGeneralDAO;
import com.example.back_vallespejo.models.dto.PresupuestoUnitarioDTO;
import com.example.back_vallespejo.models.entities.Presupuesto_General;
import com.example.back_vallespejo.models.entities.Presupuesto_unitario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class PresupuestoGeneralServiceImpl implements IPresupuestoGeneralService {
    @Autowired
    private IPresupuestoGeneralDAO dao;

    @Override
    public Presupuesto_General registrar(Presupuesto_General entity) {
        return dao.save(entity);
    }

    @Override
    public List<Presupuesto_General> getAll() {
        List<Presupuesto_General> list = new ArrayList<>();
        dao.findAll().forEach(list::add);
        return list;
    }

    @Override
    public Presupuesto_General findById(Long id) {
        return dao.findById(id).orElse(null);
    }

    @Override
    public void delete(Presupuesto_General entity) {
        dao.delete(entity);
    }
}

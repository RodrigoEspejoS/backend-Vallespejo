package com.example.back_vallespejo.service;

import com.example.back_vallespejo.models.dao.IActividadesDAO;
import com.example.back_vallespejo.models.entities.Actividades;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class ActividadesServiceImpl implements IActividadesService {
    @Autowired
    private IActividadesDAO dao;

    @Override
    public Actividades registrar(Actividades entity) {
        return dao.save(entity);
    }

    @Override
    public List<Actividades> getAll() {
        List<Actividades> list = new ArrayList<>();
        dao.findAll().forEach(list::add);
        return list;
    }

    @Override
    public Actividades findById(Long id) {
        return dao.findById(id).orElse(null);
    }

    @Override
    public void delete(Actividades entity) {
        dao.delete(entity);
    }
}

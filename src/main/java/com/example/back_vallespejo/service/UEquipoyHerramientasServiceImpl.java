package com.example.back_vallespejo.service;

import com.example.back_vallespejo.models.dao.IUEquipoyHerramientasDAO;
import com.example.back_vallespejo.models.entities.U_EquipoyHerramientas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class UEquipoyHerramientasServiceImpl implements IUEquipoyHerramientasService {
    @Autowired
    private IUEquipoyHerramientasDAO dao;

    @Override
    public U_EquipoyHerramientas registrar(U_EquipoyHerramientas entity) {
        return dao.save(entity);
    }

    @Override
    public List<U_EquipoyHerramientas> getAll() {
        List<U_EquipoyHerramientas> list = new ArrayList<>();
        dao.findAll().forEach(list::add);
        return list;
    }

    @Override
    public U_EquipoyHerramientas findById(Long id) {
        return dao.findById(id).orElse(null);
    }

    @Override
    public void delete(U_EquipoyHerramientas entity) {
        dao.delete(entity);
    }
}

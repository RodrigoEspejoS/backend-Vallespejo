package com.example.back_vallespejo.service;

import com.example.back_vallespejo.models.dao.IUItemEquipoyHerramientasDAO;
import com.example.back_vallespejo.models.entities.U_Item_EquipoyHerramientas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class UItemEquipoyHerramientasServiceImpl implements IUItemEquipoyHerramientasService {
    @Autowired
    private IUItemEquipoyHerramientasDAO dao;

    @Override
    public U_Item_EquipoyHerramientas registrar(U_Item_EquipoyHerramientas entity) {
        return dao.save(entity);
    }

    @Override
    public List<U_Item_EquipoyHerramientas> getAll() {
        List<U_Item_EquipoyHerramientas> list = new ArrayList<>();
        dao.findAll().forEach(list::add);
        return list;
    }

    @Override
    public U_Item_EquipoyHerramientas findById(Long id) {
        return dao.findById(id).orElse(null);
    }

    @Override
    public void delete(U_Item_EquipoyHerramientas entity) {
        dao.delete(entity);
    }
}

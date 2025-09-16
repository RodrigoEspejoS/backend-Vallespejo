package com.example.back_vallespejo.service;

import com.example.back_vallespejo.models.dao.IUItemManodeObraDAO;
import com.example.back_vallespejo.models.entities.U_Item_ManodeObra;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class UItemManodeObraServiceImpl implements IUItemManodeObraService {
    @Autowired
    private IUItemManodeObraDAO dao;

    @Override
    public U_Item_ManodeObra registrar(U_Item_ManodeObra entity) {
        return dao.save(entity);
    }

    @Override
    public List<U_Item_ManodeObra> getAll() {
        List<U_Item_ManodeObra> list = new ArrayList<>();
        dao.findAll().forEach(list::add);
        return list;
    }

    @Override
    public U_Item_ManodeObra findById(Long id) {
        return dao.findById(id).orElse(null);
    }

    @Override
    public void delete(U_Item_ManodeObra entity) {
        dao.delete(entity);
    }
}

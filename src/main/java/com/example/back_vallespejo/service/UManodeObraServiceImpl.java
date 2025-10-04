package com.example.back_vallespejo.service;

import com.example.back_vallespejo.models.dao.IUManodeObraDAO;
import com.example.back_vallespejo.models.entities.U_ManodeObra;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class UManodeObraServiceImpl implements IUManodeObraService {
    @Autowired
    private IUManodeObraDAO dao;

    @Override
    public U_ManodeObra registrar(U_ManodeObra entity) {
        return dao.save(entity);
    }

    @Override
    public List<U_ManodeObra> getAll() {
        List<U_ManodeObra> list = new ArrayList<>();
        dao.findAll().forEach(list::add);
        return list;
    }

    @Override
    public U_ManodeObra findById(Long id) {
        return dao.findById(id).orElse(null);
    }

    @Override
    public void delete(U_ManodeObra entity) {
        dao.delete(entity);
    }
}

package com.example.back_vallespejo.service;

import com.example.back_vallespejo.models.dao.IListaMaterialesDAO;
import com.example.back_vallespejo.models.entities.ListaMateriales;
import com.example.back_vallespejo.models.entities.Proyecto;
import com.example.back_vallespejo.models.entities.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ListaMaterialesServiceImpl implements IListaMaterialesService {

    @Autowired
    private IListaMaterialesDAO listaMaterialesDAO;


    @Override
    public ListaMateriales registrarListaMateriales(ListaMateriales listaMateriales) {
        return listaMaterialesDAO.save(listaMateriales);
    }

    @Override
    public List<ListaMateriales> getAll() {
        List<ListaMateriales> listas = new ArrayList<>();
        listaMaterialesDAO.findAll().forEach(listas::add);
        return listas;
    }

    @Override
    public ListaMateriales findById(Long id) {
        return listaMaterialesDAO.findById(id).orElse(null);
    }

    @Override
    public void delete(ListaMateriales listaMateriales) {
        listaMaterialesDAO.delete(listaMateriales);
    }

}

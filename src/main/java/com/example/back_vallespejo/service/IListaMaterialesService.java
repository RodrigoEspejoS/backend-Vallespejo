package com.example.back_vallespejo.service;

import com.example.back_vallespejo.models.entities.ListaMateriales;

import java.util.List;

public interface IListaMaterialesService {
    public List<ListaMateriales> getAll();
    public ListaMateriales registrarListaMateriales(ListaMateriales listaMateriales);
    public ListaMateriales findById(Long id);
    public void delete(ListaMateriales listaMateriales);
}

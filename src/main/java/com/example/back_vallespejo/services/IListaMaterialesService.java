package com.example.back_vallespejo.services;

import com.example.back_vallespejo.models.entities.ListaMateriales;
import com.example.back_vallespejo.models.entities.Proyecto;
import com.example.back_vallespejo.models.entities.Usuario;

import java.util.List;

public interface IListaMaterialesService {
    public List<ListaMateriales> getAll();
    public ListaMateriales registrarListaMateriales(ListaMateriales listaMateriales);
    public ListaMateriales findById(Long id);
    public void delete(ListaMateriales listaMateriales);

    //Metodos agregados a los convencionales
    public List<ListaMateriales> findByUsuario(Usuario usuario);
    public List<ListaMateriales> findByProyecto(Proyecto proyecto);
}

package com.example.back_vallespejo.models.dao;

import com.example.back_vallespejo.models.entities.ListaMateriales;
import com.example.back_vallespejo.models.entities.Usuario;
import com.example.back_vallespejo.models.entities.Proyecto;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface IListaMaterialesDAO extends CrudRepository<ListaMateriales, Long> {
    List<ListaMateriales> findByUsuario(Usuario usuario);
    List<ListaMateriales> findByProyecto(Proyecto proyecto);
}

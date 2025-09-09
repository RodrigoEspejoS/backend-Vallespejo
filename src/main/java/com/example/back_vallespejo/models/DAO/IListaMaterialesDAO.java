package com.example.back_vallespejo.models.DAO;

import com.example.back_vallespejo.models.entities.ListaMateriales;
import com.example.back_vallespejo.models.entities.Proyecto;
import com.example.back_vallespejo.models.entities.Usuario;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IListaMaterialesDAO extends CrudRepository<ListaMateriales, Long> {
    List<ListaMateriales> findByUsuario(Usuario usuario);
    List<ListaMateriales> findByProyecto(Proyecto proyecto);
}

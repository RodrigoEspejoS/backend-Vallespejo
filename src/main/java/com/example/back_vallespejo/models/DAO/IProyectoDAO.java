package com.example.back_vallespejo.models.DAO;

import com.example.back_vallespejo.models.entities.Proyecto;
import com.example.back_vallespejo.models.entities.Usuario;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IProyectoDAO extends CrudRepository<Proyecto, Long> {
    List<Proyecto> findByUsuarioResponsable(Usuario usuarioResponsable);
    List<Proyecto> findByEstado(Proyecto.EstadoProyecto estado);
    List<Proyecto> findByNombreContainingIgnoreCase(String nombre);
}

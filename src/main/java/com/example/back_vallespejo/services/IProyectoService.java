package com.example.back_vallespejo.services;

import com.example.back_vallespejo.models.dto.ProyectoCompletoDTO;
import com.example.back_vallespejo.models.dto.ProyectoDTO;
import com.example.back_vallespejo.models.entities.Proyecto;
import com.example.back_vallespejo.models.entities.Usuario;

import java.util.List;

public interface IProyectoService {
    public List<Proyecto> getAll();
    public Proyecto registrarProyecto(Proyecto proyecto);
    public Proyecto crearProyectoDesdeDTO(ProyectoDTO proyectoDTO);
    public Proyecto findById(Long id);
    public void delete(Proyecto proyecto);
    public List<Proyecto> findByUsuarioResponsable(Usuario usuarioResponsable);
    public List<Proyecto> findByEstado(Proyecto.EstadoProyecto estado);
    public List<Proyecto> findByNombreContainingIgnoreCase(String nombre);
    public ProyectoCompletoDTO getProyectoCompleto(Long id);
}

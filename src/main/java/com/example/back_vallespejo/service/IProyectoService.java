package com.example.back_vallespejo.service;

import com.example.back_vallespejo.models.dto.ProyectoDTO;
import com.example.back_vallespejo.models.dto.ProyectoCompletoDTO;
import com.example.back_vallespejo.models.entities.Proyecto;

import java.util.List;

public interface IProyectoService {
    boolean agregarActividadAProyecto(Long proyectoId, String nombreActividad);
    public List<Proyecto> getAll();
    public Proyecto crearProyectoDesdeDTO(ProyectoDTO proyectoDTO);
    public Proyecto findById(Long id);
    public Proyecto updateProyecto(Long id, ProyectoDTO proyectoDTO);
    public void delete(Proyecto proyecto);
    public List<ProyectoCompletoDTO> findByUsuarioResponsableId(Long usuarioId);
    public List<Proyecto> findByEstado(Proyecto.EstadoProyecto estado);
    public List<Proyecto> findByNombreContainingIgnoreCase(String nombre);
    public ProyectoCompletoDTO getProyectoCompleto(Long id);
}

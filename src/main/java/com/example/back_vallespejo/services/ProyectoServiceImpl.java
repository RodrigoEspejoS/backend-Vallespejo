package com.example.back_vallespejo.services;

import com.example.back_vallespejo.models.dao.IProyectoDAO;
import com.example.back_vallespejo.models.dto.ItemMaterialResponseDTO;
import com.example.back_vallespejo.models.dto.ListaMaterialesCompletaDTO;
import com.example.back_vallespejo.models.dto.ProyectoCompletoDTO;
import com.example.back_vallespejo.models.dto.ProyectoDTO;
import com.example.back_vallespejo.models.entities.ItemMaterial;
import com.example.back_vallespejo.models.entities.ListaMateriales;
import com.example.back_vallespejo.models.entities.Proyecto;
import com.example.back_vallespejo.models.entities.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProyectoServiceImpl implements IProyectoService {

    @Autowired
    private IProyectoDAO proyectoDAO;

    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private IListaMaterialesService listaMaterialesService;

    @Override
    public Proyecto registrarProyecto(Proyecto proyecto) {
        return proyectoDAO.save(proyecto);
    }

    @Override
    public Proyecto crearProyectoDesdeDTO(ProyectoDTO proyectoDTO) {
        // Buscar el usuario por ID
        Usuario usuario = usuarioService.findById(proyectoDTO.getUsuarioResponsableId());
        if (usuario == null) {
            throw new RuntimeException("Usuario no encontrado con ID: " + proyectoDTO.getUsuarioResponsableId());
        }

        // Crear el proyecto
        Proyecto proyecto = new Proyecto();
        proyecto.setNombre(proyectoDTO.getNombre());
        proyecto.setDescripcion(proyectoDTO.getDescripcion());
        proyecto.setUsuarioResponsable(usuario);
        proyecto.setFechaInicio(proyectoDTO.getFechaInicio());
        proyecto.setFechaFinEstimada(proyectoDTO.getFechaFinEstimada());
        proyecto.setPresupuesto(proyectoDTO.getPresupuesto());
        proyecto.setUbicacion(proyectoDTO.getUbicacion());
        proyecto.actualizarFechaModificacion(); // Establecer fecha de creación/actualización
        
        // Asignar estado por defecto si no se especifica
        if (proyectoDTO.getEstado() != null) {
            proyecto.setEstado(Proyecto.EstadoProyecto.valueOf(proyectoDTO.getEstado()));
        } else {
            proyecto.setEstado(Proyecto.EstadoProyecto.PLANIFICACION);
        }

        // Manejar la lista de materiales
        ListaMateriales listaMateriales;
        if (proyectoDTO.getListaMaterialesId() != null) {
            // Usar lista de materiales existente
            listaMateriales = listaMaterialesService.findById(proyectoDTO.getListaMaterialesId());
            if (listaMateriales == null) {
                throw new RuntimeException("Lista de materiales no encontrada con ID: " + proyectoDTO.getListaMaterialesId());
            }
        } else {
            // Crear una lista de materiales nueva
            listaMateriales = new ListaMateriales();
            listaMateriales.setNombre("Lista de materiales - " + proyecto.getNombre());
            listaMateriales.setUsuario(usuario); // Asignar el usuario
            listaMateriales.setFechaCreacion(LocalDateTime.now());
            listaMateriales.setEstado(ListaMateriales.EstadoLista.PENDIENTE); // Asignar estado por defecto
        }
        
        proyecto.setListaMateriales(listaMateriales);
        listaMateriales.setProyecto(proyecto);

        return proyectoDAO.save(proyecto);
    }

    @Override
    public List<Proyecto> getAll() {
        List<Proyecto> proyectos = new ArrayList<>();
        proyectoDAO.findAll().forEach(proyectos::add);
        return proyectos;
    }

    @Override
    public Proyecto findById(Long id) {
        return proyectoDAO.findById(id).orElse(null);
    }

    @Override
    public void delete(Proyecto proyecto) {
        proyectoDAO.delete(proyecto);
    }

    @Override
    public List<Proyecto> findByUsuarioResponsable(Usuario usuarioResponsable) {
        return proyectoDAO.findByUsuarioResponsable(usuarioResponsable);
    }

    @Override
    public List<Proyecto> findByEstado(Proyecto.EstadoProyecto estado) {
        return proyectoDAO.findByEstado(estado);
    }

    @Override
    public List<Proyecto> findByNombreContainingIgnoreCase(String nombre) {
        return proyectoDAO.findByNombreContainingIgnoreCase(nombre);
    }

    @Override
    public ProyectoCompletoDTO getProyectoCompleto(Long id) {
        Proyecto proyecto = proyectoDAO.findById(id).orElse(null);
        if (proyecto == null) {
            return null;
        }

        // Nota Rodrigo: Obtiene listas de materiales por el id de proyecto desde el service ListaMateriales
        List<ListaMateriales> listasMateriales = listaMaterialesService.findByProyecto(proyecto);
        
        // Convertir las listas a DTOs completos
        List<ListaMaterialesCompletaDTO> listasCompletasDTO = listasMateriales.stream()
            .map(lista -> {
                // Obtener los items de cada lista
                List<ItemMaterial> items = lista.getItems();
                List<ItemMaterialResponseDTO> itemsDTO = items.stream()
                    .map(item -> new ItemMaterialResponseDTO(
                        item.getId(),
                        item.getMaterial().getNombre(),
                        item.getMaterial().getSerie(),
                        item.getCantidad(),
                        item.getPrecioUnitario(),
                        item.getSubtotal(),
                        item.getObservaciones(),
                        item.getFechaAgregado(),
                        item.getListaMateriales().getId(),
                        item.getListaMateriales().getNombre()
                    ))
                    .collect(Collectors.toList());

                return new ListaMaterialesCompletaDTO(
                    lista.getId(),
                    lista.getNombre(),
                    lista.getDescripcion(),
                    lista.getFechaCreacion(),
                    lista.getFechaActualizacion(),
                    lista.getTotalEstimado(),
                    itemsDTO
                );
            })
            .collect(Collectors.toList());

        // Crear el DTO del proyecto completo
        return new ProyectoCompletoDTO(
            proyecto.getId(),
            proyecto.getNombre(),
            proyecto.getDescripcion(),
            proyecto.getUbicacion(),
            proyecto.getFechaInicio().atStartOfDay(), // Convertir LocalDate a LocalDateTime
            proyecto.getFechaFinEstimada().atStartOfDay(), // Convertir LocalDate a LocalDateTime
            proyecto.getEstado().toString(),
            proyecto.getPresupuesto(),
            proyecto.getFechaCreacion(),
            proyecto.getFechaActualizacion(),
            proyecto.getUsuarioResponsable().getNombre(),
            listasCompletasDTO
        );
    }
}

package com.example.back_vallespejo.service;

import com.example.back_vallespejo.models.dao.IProyectoDAO;
import com.example.back_vallespejo.models.dto.ProyectoDTO;
import com.example.back_vallespejo.models.dto.ProyectoCompletoDTO;
import com.example.back_vallespejo.models.dto.PresupuestoGeneralDTO;
import com.example.back_vallespejo.models.entities.Presupuesto_General;
import com.example.back_vallespejo.models.entities.Proyecto;
import com.example.back_vallespejo.models.entities.Usuario;
import com.example.back_vallespejo.models.dao.IPresupuestoGeneralDAO;
import com.example.back_vallespejo.models.dao.IActividadesDAO;
import com.example.back_vallespejo.models.dao.IPresupuestoUnitarioDAO;
import com.example.back_vallespejo.models.dao.IUEquipoyHerramientasDAO;
import com.example.back_vallespejo.models.dao.IListaMaterialesDAO;
import com.example.back_vallespejo.models.dao.IUManodeObraDAO;
import com.example.back_vallespejo.models.entities.Actividades;
import com.example.back_vallespejo.models.entities.Presupuesto_unitario;
import com.example.back_vallespejo.models.entities.U_EquipoyHerramientas;
import com.example.back_vallespejo.models.entities.ListaMateriales;
import com.example.back_vallespejo.models.entities.U_ManodeObra;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ProyectoServiceImpl implements IProyectoService {

    @Autowired
    private IPresupuestoGeneralDAO presupuestoGeneralDAO;
    @Autowired
    private IActividadesDAO actividadesDAO;
    @Autowired
    private IPresupuestoUnitarioDAO presupuestoUnitarioDAO;
    @Autowired
    private IUEquipoyHerramientasDAO uEquipoyHerramientasDAO;
    @Autowired
    private IListaMaterialesDAO listaMaterialesDAO;
    @Autowired
    private IUManodeObraDAO uManodeObraDAO;

    @Autowired
    private IProyectoDAO proyectoDAO;

    @Autowired
    private IUsuarioService usuarioService;


    @Override
    public boolean agregarActividadAProyecto(Long proyectoId, String nombreActividad) {
        Proyecto proyecto = proyectoDAO.findById(proyectoId).orElse(null);
        if (proyecto == null || proyecto.getPresupuestoGeneral() == null) {
            return false;
        }
        Presupuesto_General presupuestoGeneral = proyecto.getPresupuestoGeneral();

        // Crear actividad
    Actividades actividad = new Actividades();
        actividad.setNombre(nombreActividad);
        actividad.setDescripcion("Actividad " + nombreActividad + " para " + proyecto.getNombre());
        actividad.setPresupuestoGeneral(presupuestoGeneral);
        actividad.setEstado("En curso");

        // Crear presupuesto unitario y sus 3 listas
    Presupuesto_unitario presupuestoUnitario = new Presupuesto_unitario();
        String descPU = (nombreActividad);
        if (descPU.length() > 50) {
            descPU = descPU.substring(0,50);
        }
        presupuestoUnitario.setDescripcion(descPU);

    U_EquipoyHerramientas listaEquipos = new U_EquipoyHerramientas();
    // Lista de materiales requiere nombre, estado y fechaCreacion no nulos según entidad
    ListaMateriales listaMateriales = new ListaMateriales();
    listaMateriales.setNombre("Lista materiales " + nombreActividad);
    listaMateriales.setFechaCreacion(java.time.LocalDateTime.now());
    listaMateriales.setEstado(ListaMateriales.EstadoLista.PENDIENTE);
    listaMateriales.setTotalEstimado(0.0);
    U_ManodeObra listaManoObra = new U_ManodeObra();

        uEquipoyHerramientasDAO.save(listaEquipos); // sub total inicializado a 0 en la entidad
        listaMaterialesDAO.save(listaMateriales);
        uManodeObraDAO.save(listaManoObra);

        presupuestoUnitario.setUEquipoyHerramientas(listaEquipos);
        presupuestoUnitario.setListaMateriales(listaMateriales);
        presupuestoUnitario.setUManodeObra(listaManoObra);
        presupuestoUnitarioDAO.save(presupuestoUnitario);

        actividad.setPresupuestoUnitario(presupuestoUnitario);
        actividadesDAO.save(actividad);

        // Vincular actividad al presupuesto general
    List<Actividades> actividades = presupuestoGeneral.getActividades();
        if (actividades == null) {
            actividades = new ArrayList<>();
        }
        actividades.add(actividad);
        presupuestoGeneral.setActividades(actividades);
        presupuestoGeneralDAO.save(presupuestoGeneral);

        return true;
    }

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
        proyecto.actualizarFechaModificacion();

        // Asignar estado por defecto si no se especifica
        if (proyectoDTO.getEstado() != null && !proyectoDTO.getEstado().isBlank()) {
            try {
                proyecto.setEstado(Proyecto.EstadoProyecto.valueOf(proyectoDTO.getEstado().toUpperCase()));
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Estado invalido. Valores permitidos: PLANIFICACION, APROBADO, EN_EJECUCION, PAUSADO, COMPLETADO, CANCELADO, RECHAZADO");
            }
        } else {
            proyecto.setEstado(Proyecto.EstadoProyecto.PLANIFICACION);
        }

        // Crear y vincular Presupuesto_General
        Presupuesto_General presupuestoGeneral = new Presupuesto_General();
        presupuestoGeneral.setDescripcion("Presupuesto general para proyecto " + proyecto.getNombre());

        proyecto.setPresupuestoGeneral(presupuestoGeneral);
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
    public Proyecto updateProyecto(Long id, ProyectoDTO proyectoDTO) {
        // Buscar el proyecto existente
        Proyecto proyectoExistente = proyectoDAO.findById(id).orElse(null);
        if (proyectoExistente == null) {
            return null;
        }

        // Actualizar solo los campos que no sean nulos o vacíos
        if (proyectoDTO.getNombre() != null && !proyectoDTO.getNombre().trim().isEmpty()) {
            proyectoExistente.setNombre(proyectoDTO.getNombre());
        }
        
        if (proyectoDTO.getDescripcion() != null) {
            proyectoExistente.setDescripcion(proyectoDTO.getDescripcion());
        }
        
        if (proyectoDTO.getUsuarioResponsableId() != null) {
            Usuario usuario = usuarioService.findById(proyectoDTO.getUsuarioResponsableId());
            if (usuario == null) {
                throw new RuntimeException("Usuario no encontrado con ID: " + proyectoDTO.getUsuarioResponsableId());
            }
            proyectoExistente.setUsuarioResponsable(usuario);
        }
        
        if (proyectoDTO.getFechaInicio() != null) {
            proyectoExistente.setFechaInicio(proyectoDTO.getFechaInicio());
        }
        
        if (proyectoDTO.getFechaFinEstimada() != null) {
            proyectoExistente.setFechaFinEstimada(proyectoDTO.getFechaFinEstimada());
        }
        
        if (proyectoDTO.getPresupuesto() != null) {
            proyectoExistente.setPresupuesto(proyectoDTO.getPresupuesto());
        }
        
        if (proyectoDTO.getUbicacion() != null) {
            proyectoExistente.setUbicacion(proyectoDTO.getUbicacion());
        }
        
        if (proyectoDTO.getEstado() != null && !proyectoDTO.getEstado().isBlank()) {
            try {
                proyectoExistente.setEstado(Proyecto.EstadoProyecto.valueOf(proyectoDTO.getEstado().toUpperCase()));
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Estado invalido. Valores permitidos: PLANIFICACION, APROBADO, EN_EJECUCION, PAUSADO, COMPLETADO, CANCELADO, RECHAZADO");
            }
        }

        // Actualizar fecha de modificación
        proyectoExistente.actualizarFechaModificacion();
        
        return proyectoDAO.save(proyectoExistente);
    }

    @Override
    public ProyectoCompletoDTO getProyectoCompleto(Long id) {
        Proyecto proyecto = proyectoDAO.findById(id).orElse(null);
        if (proyecto == null) {
            return null;
        }
        String usuarioNombre = proyecto.getUsuarioResponsable() != null ? proyecto.getUsuarioResponsable().getNombre() : null;
        Presupuesto_General pg = proyecto.getPresupuestoGeneral();
        PresupuestoGeneralDTO pgDTO = null;
        if (pg != null) {
            pgDTO = new PresupuestoGeneralDTO();
            pgDTO.setId(pg.getId());
            pgDTO.setDescripcion(pg.getDescripcion());
            pgDTO.setCantidadActividades(pg.getActividades()!=null ? pg.getActividades().size() : 0);
        }
        return new ProyectoCompletoDTO(
                proyecto.getId(),
                proyecto.getNombre(),
                proyecto.getDescripcion(),
                proyecto.getUbicacion(),
                proyecto.getFechaInicio() != null ? proyecto.getFechaInicio().atStartOfDay() : null,
                proyecto.getFechaFinEstimada() != null ? proyecto.getFechaFinEstimada().atStartOfDay() : null,
                proyecto.getEstado() != null ? proyecto.getEstado().name() : null,
                proyecto.getPresupuesto(),
                proyecto.getFechaCreacion(),
                proyecto.getFechaActualizacion(),
                usuarioNombre,
                pgDTO
        );
    }
}

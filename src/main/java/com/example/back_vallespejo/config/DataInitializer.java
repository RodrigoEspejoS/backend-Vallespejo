package com.example.back_vallespejo.config;

import com.example.back_vallespejo.models.dto.UsuarioDTO;
import com.example.back_vallespejo.models.entities.Rol;
import com.example.back_vallespejo.models.entities.Usuario;
import com.example.back_vallespejo.service.IRolService;
import com.example.back_vallespejo.service.IUsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private IRolService rolService;

    @Autowired
    private IUsuarioService usuarioService;

    @Override
    public void run(String... args) throws Exception {

        //Todo para Rol . los 3 principales al inciiar

        List<Rol> rolesExistentes = rolService.getAll();
        
        if (rolesExistentes.isEmpty()) {
        
            Rol admin = new Rol();
            admin.setNombre("ADMIN");
            admin.setDescripcion("Administrador del sistema con acceso total");
            rolService.registrarRol(admin);
            
            // Crear rol GERENTE
            Rol gerente = new Rol();
            gerente.setNombre("USUARIO");
            gerente.setDescripcion("Usuario con permisos para crear proyectos");
            rolService.registrarRol(gerente);
            
            Rol empleado = new Rol();
            empleado.setNombre("TEMPORAL");
            empleado.setDescripcion("Un usuario temporal con permisos limitados");
            rolService.registrarRol(empleado);
            
        } else {
            System.out.println("los roles ya existen");
        }

        // Para Usuario admin por defecto

        List<Usuario> usuarioExiste = usuarioService.getAll();

        if(usuarioExiste.isEmpty()){

            UsuarioDTO usuario = new UsuarioDTO();
            usuario.setDni("00000000");
            usuario.setNombre("Admin");
            usuario.setApellido("Administrador");
            usuario.setPassword("admin123");
            usuario.setEmail("admin@admin.com");
            usuario.setNombreRol("ADMIN");
            usuarioService.registrarUsuarioDTO(usuario);
        }
    }
}

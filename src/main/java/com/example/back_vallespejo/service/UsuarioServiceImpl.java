package com.example.back_vallespejo.service;

import com.example.back_vallespejo.models.dao.IUsuarioDAO;
import com.example.back_vallespejo.models.dao.IRolDAO;
import com.example.back_vallespejo.models.entities.Usuario;
import com.example.back_vallespejo.models.entities.Rol;
import com.example.back_vallespejo.models.dto.UsuarioDTO;
import com.example.back_vallespejo.models.dto.CambiarPasswordDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioServiceImpl implements IUsuarioService{

    @Autowired
    private IUsuarioDAO usuarioDAO;

    @Autowired
    private IRolDAO rolDAO;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public Usuario registrarUsuarioDTO(UsuarioDTO usuarioDTO) {
        Rol rol = rolDAO.findByNombre(usuarioDTO.getNombreRol())
                .orElseThrow(() -> new RuntimeException("Rol no encontrado: " + usuarioDTO.getNombreRol()));

        Usuario usuario = new Usuario();
        usuario.setDni(usuarioDTO.getDni());
        usuario.setNombre(usuarioDTO.getNombre());
        usuario.setApellido(usuarioDTO.getApellido());
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setRol(rol);
        usuario.setEstado(1);

        String hashedPassword = passwordEncoder.encode(usuarioDTO.getPassword());
        usuario.setPassword(hashedPassword);

        return usuarioDAO.save(usuario);
    }

    @Override
    public List<Usuario> getAll() {
        List<Usuario> usuarios= new ArrayList<>();
        usuarioDAO.findAll().forEach(usuarios::add);

        return usuarios;
    }

    @Override
    public Usuario findById(Long id) {
        return usuarioDAO.findById(id).orElse(null);
    }

    @Override
    public Usuario findByEmail(String email){ 
        return usuarioDAO.findByEmail(email).orElse(null);
    }

    @Override
    public Usuario updateUsuario(Long id, UsuarioDTO usuarioDTO) {
        Usuario usuarioExistente = usuarioDAO.findById(id).orElse(null);
        if (usuarioExistente == null) {
            return null;
        }

        if (usuarioDTO.getDni() != null && !usuarioDTO.getDni().trim().isEmpty()) {
            usuarioExistente.setDni(usuarioDTO.getDni());
        }
        if (usuarioDTO.getEmail() != null && !usuarioDTO.getEmail().trim().isEmpty()) {
            usuarioExistente.setEmail(usuarioDTO.getEmail());
        }
        if (usuarioDTO.getNombre() != null && !usuarioDTO.getNombre().trim().isEmpty()) {
            usuarioExistente.setNombre(usuarioDTO.getNombre());
        }
        if (usuarioDTO.getApellido() != null && !usuarioDTO.getApellido().trim().isEmpty()) {
            usuarioExistente.setApellido(usuarioDTO.getApellido());
        }
        if (usuarioDTO.getPassword() != null && !usuarioDTO.getPassword().trim().isEmpty()) {
            usuarioExistente.setPassword(passwordEncoder.encode(usuarioDTO.getPassword()));
        }
        if (usuarioDTO.getNombreRol() != null && !usuarioDTO.getNombreRol().trim().isEmpty()) {
            Rol nuevoRol = rolDAO.findByNombre(usuarioDTO.getNombreRol()).orElse(null);
            if (nuevoRol != null) {
                usuarioExistente.setRol(nuevoRol);
            }
        }

        return usuarioDAO.save(usuarioExistente);
    }

    @Override
    public boolean cambiarPassword(Long id, CambiarPasswordDTO cambiarPasswordDTO) {
        Usuario usuario = usuarioDAO.findById(id).orElse(null);
        if (usuario == null) {
            return false; // Usuario no encontrado
        }

        // Verificar que la contraseña actual sea correcta
        if (!passwordEncoder.matches(cambiarPasswordDTO.getContraseñaActual(), usuario.getPassword())) {
            return false; // Contraseña actual incorrecta
        }

        // Actualizar con la nueva contraseña encriptada
        usuario.setPassword(passwordEncoder.encode(cambiarPasswordDTO.getNuevaContraseña()));
        usuarioDAO.save(usuario);
        
        return true; // Cambio exitoso
    }

    @Override
    public void delete(Usuario usuario) {
        usuarioDAO.delete(usuario);
    }
}

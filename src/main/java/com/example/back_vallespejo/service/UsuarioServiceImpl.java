package com.example.back_vallespejo.service;

import com.example.back_vallespejo.models.dao.IUsuarioDAO;
import com.example.back_vallespejo.models.dao.IRolDAO;
import com.example.back_vallespejo.models.entities.Usuario;
import com.example.back_vallespejo.models.entities.Rol;
import com.example.back_vallespejo.models.dto.UsuarioDTO;
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
        // Buscar el rol por nombre
        Rol rol = rolDAO.findByNombre(usuarioDTO.getNombreRol())
                .orElseThrow(() -> new RuntimeException("Rol no encontrado: " + usuarioDTO.getNombreRol()));

        // Crear nuevo usuario a partir del DTO
        Usuario usuario = new Usuario();
        usuario.setDni(usuarioDTO.getDni());
        usuario.setNombre(usuarioDTO.getNombre());
        usuario.setApellido(usuarioDTO.getApellido());
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setRol(rol);
        usuario.setEstado(1);

        // Encriptar la contraseña
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
    public Usuario findByEmail(String email){ return usuarioDAO.findByEmail(email).orElse(null);}

    @Override
    public void delete(Usuario usuario) {
        usuarioDAO.delete(usuario);
    }
}

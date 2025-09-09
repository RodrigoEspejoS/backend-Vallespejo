package com.example.back_vallespejo.services;

import com.example.back_vallespejo.models.dto.UsuarioDTO;
import com.example.back_vallespejo.models.entities.Usuario;

import java.util.List;

public interface IUsuarioService {
    public List<Usuario> getAll();
    public Usuario registrarUsuarioDTO(UsuarioDTO usuarioDTO);
    public Usuario findById(Long id);
    public void delete(Usuario usuario);
}

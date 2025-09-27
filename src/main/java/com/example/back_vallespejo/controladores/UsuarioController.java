package com.example.back_vallespejo.controladores;

import com.example.back_vallespejo.models.entities.Usuario;
import com.example.back_vallespejo.models.dto.UsuarioDTO;
import com.example.back_vallespejo.models.dto.CambiarPasswordDTO;
import com.example.back_vallespejo.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class UsuarioController {

    @Autowired
    private IUsuarioService usuarioService;

    /*Get para obtener todos los usuarios*/
    @GetMapping("/usuario")
    public List<Usuario> getAll(){

        return usuarioService.getAll();
    }

    @PostMapping("/usuario")
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario registrarUsuarioSimple(@RequestBody UsuarioDTO usuarioDTO){

        return usuarioService.registrarUsuarioDTO(usuarioDTO);
    }

    @GetMapping("/usuario/{id}")
    public Usuario getUsuarioById(@PathVariable Long id){

        return usuarioService.findById(id);
    }

    @GetMapping("/usuario/email/{email}")
    public Usuario getUsuarioByEmail(@PathVariable String email){

        return usuarioService.findByEmail(email);
    }

    @PutMapping("/usuario/{id}")
    public ResponseEntity<Usuario> editarUsuario(@PathVariable Long id, @RequestBody UsuarioDTO usuarioDTO){
        Usuario usuarioActualizado = usuarioService.updateUsuario(id, usuarioDTO);
        
        if(usuarioActualizado == null){
            return ResponseEntity.notFound().build();
        }
        
        return ResponseEntity.ok(usuarioActualizado);
    }

    @PutMapping("/usuario/{id}/password")
    public ResponseEntity<String> cambiarPassword(@PathVariable Long id, @RequestBody CambiarPasswordDTO cambiarPasswordDTO){
        boolean cambioExitoso = usuarioService.cambiarPassword(id, cambiarPasswordDTO);
        
        if(!cambioExitoso){
            return ResponseEntity.badRequest().body("No se pudo cambiar la contraseña. Verifique que el usuario exista y la contraseña actual sea correcta.");
        }
        
        return ResponseEntity.ok("Contraseña cambiada exitosamente");
    }

    @DeleteMapping("/usuario/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarUsuario(@PathVariable Long id){
        Usuario usuario = usuarioService.findById(id);
        if(usuario != null){
            usuarioService.delete(usuario);
        }
    }
}

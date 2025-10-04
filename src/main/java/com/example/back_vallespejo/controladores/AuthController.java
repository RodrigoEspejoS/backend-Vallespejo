package com.example.back_vallespejo.controladores;

import com.example.back_vallespejo.models.dto.LoginRequest;
import com.example.back_vallespejo.models.dto.LoginResponse;
import com.example.back_vallespejo.models.entities.Usuario;
import com.example.back_vallespejo.security.JwtUtil;
import com.example.back_vallespejo.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthController {

    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            // Buscar usuario por email (username es el email)
            Usuario usuario = usuarioService.findByEmail(loginRequest.getUsername());
            
            if (usuario == null) {
                return ResponseEntity.badRequest().body("Usuario no encontrado");
            }

            // Verificar la contraseña
            if (!passwordEncoder.matches(loginRequest.getPassword(), usuario.getPassword())) {
                return ResponseEntity.badRequest().body("Contraseña incorrecta");
            }

            // Generar token JWT con el rol del usuario
            String rolNombre = usuario.getRol() != null ? usuario.getRol().getNombre() : "USER";
            String token = jwtUtil.generateToken(usuario.getEmail(), rolNombre);

            // Crear respuesta con el token
            LoginResponse response = new LoginResponse(
                    token,
                    usuario.getEmail(),
                    rolNombre,
                    usuario.getId()
            );

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error en el login: " + e.getMessage());
        }
    }

    @GetMapping("/validate")
    public ResponseEntity<?> validateToken(@RequestHeader("Authorization") String authHeader) {
        try {
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                String token = authHeader.substring(7);
                String username = jwtUtil.extractUsername(token);
                
                if (jwtUtil.validateToken(token, username)) {
                    return ResponseEntity.ok("Token válido");
                }
            }
            return ResponseEntity.badRequest().body("Token inválido");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al validar token: " + e.getMessage());
        }
    }
}

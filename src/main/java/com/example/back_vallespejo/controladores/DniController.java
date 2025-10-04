package com.example.back_vallespejo.controladores;

import com.example.back_vallespejo.models.dto.DniResponseDTO;
import com.example.back_vallespejo.service.IDniService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class DniController {

    @Autowired
    private IDniService dniService;

    /**
     * Consulta la información de una persona por su número de DNI
     * Ejemplo: GET /api/dni/46027897
     */
    @GetMapping("/dni/{numero}")
    public ResponseEntity<?> consultarDni(@PathVariable String numero) {
        try {
            // Validar que el número de DNI tenga 8 dígitos
            if (numero == null || !numero.matches("\\d{8}")) {
                return ResponseEntity.badRequest()
                    .body("El número de DNI debe tener 8 dígitos");
            }

            // Consultar la API externa
            DniResponseDTO resultado = dniService.consultarDni(numero);

            if (resultado == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontró información para el DNI: " + numero);
            }

            return ResponseEntity.ok(resultado);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error al consultar DNI: " + e.getMessage());
        }
    }
}

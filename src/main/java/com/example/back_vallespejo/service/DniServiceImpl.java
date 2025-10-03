package com.example.back_vallespejo.service;

import com.example.back_vallespejo.models.dto.DniResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DniServiceImpl implements IDniService {

    private static final String API_URL = "https://api.decolecta.com/v1/reniec/dni";
    private static final String API_TOKEN = "sk_1959.J6a4V593IO64NNcKJbaAfOzjEB2iZ752";

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public DniResponseDTO consultarDni(String numeroDni) {
        try {
            // Construir la URL con el parámetro del número de DNI
            String url = API_URL + "?numero=" + numeroDni;

            // Configurar los headers con el token de autenticación
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + API_TOKEN);
            headers.set("Content-Type", "application/json");

            // Crear la entidad HTTP con los headers
            HttpEntity<String> entity = new HttpEntity<>(headers);

            // Hacer la petición GET a la API
            ResponseEntity<DniResponseDTO> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                DniResponseDTO.class
            );

            // Retornar el body de la respuesta
            return response.getBody();

        } catch (Exception e) {
            throw new RuntimeException("Error al consultar DNI: " + e.getMessage(), e);
        }
    }
}

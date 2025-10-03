package com.example.back_vallespejo.service;

import com.example.back_vallespejo.models.dto.DniResponseDTO;

public interface IDniService {
    
    /**
     * Consulta la información de una persona por su número de DNI
     * @param numeroDni Número de DNI a consultar
     * @return DniResponseDTO con la información de la persona
     */
    DniResponseDTO consultarDni(String numeroDni);
}

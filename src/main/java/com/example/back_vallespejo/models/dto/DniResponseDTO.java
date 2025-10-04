package com.example.back_vallespejo.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DniResponseDTO {

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("first_last_name")
    private String firstLastName;

    @JsonProperty("second_last_name")
    private String secondLastName;

    @JsonProperty("full_name")
    private String fullName;

    @JsonProperty("document_number")
    private String documentNumber;

    // Constructor vacío
    public DniResponseDTO() {
    }

    // Constructor con todos los campos
    public DniResponseDTO(String firstName, String firstLastName, String secondLastName, 
                          String fullName, String documentNumber) {
        this.firstName = firstName;
        this.firstLastName = firstLastName;
        this.secondLastName = secondLastName;
        this.fullName = fullName;
        this.documentNumber = documentNumber;
    }

    // Getters y Setters
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstLastName() {
        return firstLastName;
    }

    public void setFirstLastName(String firstLastName) {
        this.firstLastName = firstLastName;
    }

    public String getSecondLastName() {
        return secondLastName;
    }

    public void setSecondLastName(String secondLastName) {
        this.secondLastName = secondLastName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    @Override
    public String toString() {
        return "DniResponseDTO{" +
                "firstName='" + firstName + '\'' +
                ", firstLastName='" + firstLastName + '\'' +
                ", secondLastName='" + secondLastName + '\'' +
                ", fullName='" + fullName + '\'' +
                ", documentNumber='" + documentNumber + '\'' +
                '}';
    }
}

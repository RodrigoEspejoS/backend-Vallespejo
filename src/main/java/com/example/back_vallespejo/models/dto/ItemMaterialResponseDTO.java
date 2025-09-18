package com.example.back_vallespejo.models.dto;


/* Nota Rodri: Este DTO es especificamente solo para respuesta JSON (get) */

public class ItemMaterialResponseDTO {
    
    private Long id;
    private String materialNombre;
    private String materialSerie;
    private Integer cantidad;
    private Double precioUnitario;
    private Double subtotal;
    private Long listaMaterialesId;
    private String listaMaterialesNombre;

    public ItemMaterialResponseDTO() {}

    public ItemMaterialResponseDTO(Long id, String materialNombre, String materialSerie, 
                                 Integer cantidad, Double precioUnitario, Double subtotal, 
                                 Long listaMaterialesId, String listaMaterialesNombre) {
        this.id = id;
        this.materialNombre = materialNombre;
        this.materialSerie = materialSerie;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.subtotal = subtotal;
        this.listaMaterialesId = listaMaterialesId;
        this.listaMaterialesNombre = listaMaterialesNombre;
    }

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMaterialNombre() {
        return materialNombre;
    }

    public void setMaterialNombre(String materialNombre) {
        this.materialNombre = materialNombre;
    }

    public String getMaterialSerie() {
        return materialSerie;
    }

    public void setMaterialSerie(String materialSerie) {
        this.materialSerie = materialSerie;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(Double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public Long getListaMaterialesId() {
        return listaMaterialesId;
    }

    public void setListaMaterialesId(Long listaMaterialesId) {
        this.listaMaterialesId = listaMaterialesId;
    }

    public String getListaMaterialesNombre() {
        return listaMaterialesNombre;
    }

    public void setListaMaterialesNombre(String listaMaterialesNombre) {
        this.listaMaterialesNombre = listaMaterialesNombre;
    }
}

package com.example.back_vallespejo.models.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "presupuestos_unitarios")
public class Presupuesto_unitario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    private String descripcion;

    //tipo de unidad (glb,und)
    @Column(length = 10)
    private String unidad;

    //unidad de rendimiento
    private Double u_rendimiento;

    //tiempo de rendimiento
    @Column(length = 20)
    private String t_rendimiento;

    //este será el total de la suma de sub total Mano de obra, Lista de materiales y EquiposyHerramientas
    private Double Total_presupuesto_unitario;

    //presupuesto parcial: Total_presupuesto_unitario * u_rendimiento
    private Double Total_presupuesto_parcial;

    //cada presupuesto unitario está vinculado a su propia lista de Mano de obra, servicios generales y Materiales
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "u_equipoyherramientas_id", referencedColumnName = "id", nullable = false)
    private U_EquipoyHerramientas uEquipoyHerramientas;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "lista_materiales_id", referencedColumnName = "id", nullable = false)
    private ListaMateriales listaMateriales;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "u_manodeobra_id", referencedColumnName = "id", nullable = false)
    private U_ManodeObra uManodeObra;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public Double getU_rendimiento() {
        return u_rendimiento;
    }

    public void setU_rendimiento(Double u_rendimiento) {
        this.u_rendimiento = u_rendimiento;
    }

    public String getT_rendimiento() {
        return t_rendimiento;
    }

    public void setT_rendimiento(String t_rendimiento) {
        this.t_rendimiento = t_rendimiento;
    }

    public void setTotal_presupuesto_unitario(Double total_presupuesto_unitario) {
        Total_presupuesto_unitario = total_presupuesto_unitario;
    }

    public U_EquipoyHerramientas getUEquipoyHerramientas() {
        return uEquipoyHerramientas;
    }

    public void setUEquipoyHerramientas(U_EquipoyHerramientas uEquipoyHerramientas) {
        this.uEquipoyHerramientas = uEquipoyHerramientas;
    }

    public ListaMateriales getListaMateriales() {
        return listaMateriales;
    }

    public void setListaMateriales(ListaMateriales listaMateriales) {
        this.listaMateriales = listaMateriales;
    }

    public U_ManodeObra getUManodeObra() {
        return uManodeObra;
    }

    public void setuManodeObra(U_ManodeObra uManodeObra) {
        this.uManodeObra = uManodeObra;
    }

    public void setUManodeObra(U_ManodeObra uManodeObra) {
        this.uManodeObra = uManodeObra;
    }

    public Double getTotal_presupuesto_parcial() {
        // Calcular automáticamente el presupuesto parcial cada vez que se solicita
        return calcularPresupuestoParcial();
    }

    public void setTotal_presupuesto_parcial(Double total_presupuesto_parcial) {
        this.Total_presupuesto_parcial = total_presupuesto_parcial;
    }
        public Double getTotal_presupuesto_unitario() {
            double total = 0.0;
            if (uEquipoyHerramientas != null) {
                total += uEquipoyHerramientas.getSubtotal();
            }
            if (listaMateriales != null) {
                total += listaMateriales.getTotalEstimado();
            }
            if (uManodeObra != null) {
                total += uManodeObra.getSubtotal();
            }
            this.Total_presupuesto_unitario = total;
            return Total_presupuesto_unitario;
        }
        
        // Método para calcular el presupuesto parcial: Total_presupuesto_unitario * u_rendimiento
        public Double calcularPresupuestoParcial() {
            // Primero asegurar que el total unitario esté actualizado
            getTotal_presupuesto_unitario();
            
            // Calcular presupuesto parcial: Total_presupuesto_unitario * u_rendimiento
            if (u_rendimiento != null && u_rendimiento > 0 && Total_presupuesto_unitario != null) {
                this.Total_presupuesto_parcial = Total_presupuesto_unitario * u_rendimiento;
            } else {
                this.Total_presupuesto_parcial = 0.0;
            }
            
            return Total_presupuesto_parcial;
        }
}

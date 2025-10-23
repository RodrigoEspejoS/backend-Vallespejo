package com.example.back_vallespejo.service;

import com.example.back_vallespejo.models.dto.RendimientoInfoDTO;
import org.springframework.stereotype.Component;

/**
 * Calculadora de rendimiento para actividades de construcción.
 * Implementa fórmulas realistas basadas en estándares de la industria.
 */
@Component
public class RendimientoCalculator {

    /**
     * Calcula el rendimiento automático basado en el tipo de actividad
     * y parámetros de complejidad.
     * 
     * Fórmula: Rendimiento = RendimientoBase * FactorComplejidad * FactorTipo
     * 
     * @param nombreActividad Nombre descriptivo de la actividad
     * @param descripcion Descripción detallada
     * @return Rendimiento calculado (unidades que se pueden realizar)
     */
    public Double calcularRendimiento(String nombreActividad, String descripcion) {
        // Rendimiento base (unidades por jornada estándar de 8 horas)
        double rendimientoBase = determinarRendimientoBase(nombreActividad, descripcion);
        
        // Factor de complejidad basado en palabras clave
        double factorComplejidad = determinarFactorComplejidad(nombreActividad, descripcion);
        
        // Calcular rendimiento final
        double rendimiento = rendimientoBase * factorComplejidad;
        
        // Redondear a 2 decimales
        return Math.round(rendimiento * 100.0) / 100.0;
    }

    /**
     * Determina el rendimiento base según el tipo de actividad.
     * Basado en estándares de construcción peruanos e internacionales.
     */
    private double determinarRendimientoBase(String nombreActividad, String descripcion) {
        String textoCompleto = (nombreActividad + " " + descripcion).toLowerCase();
        
        // MOVIMIENTO DE TIERRAS
        if (contienePatron(textoCompleto, "excavacion", "excavar", "zanja")) {
            return 15.0; // m³/día
        }
        if (contienePatron(textoCompleto, "relleno", "compactacion", "compactar")) {
            return 20.0; // m³/día
        }
        if (contienePatron(textoCompleto, "nivelacion", "nivelar", "explanacion")) {
            return 50.0; // m²/día
        }
        
        // CONCRETO
        if (contienePatron(textoCompleto, "concreto", "vaciado", "losa", "viga", "columna")) {
            if (contienePatron(textoCompleto, "losa", "piso")) {
                return 12.0; // m³/día
            }
            if (contienePatron(textoCompleto, "viga", "columna", "zapata")) {
                return 8.0; // m³/día
            }
            return 10.0; // m³/día
        }
        
        // ENCOFRADO Y DESENCOFRADO
        if (contienePatron(textoCompleto, "encofrado", "formaleta")) {
            return 10.0; // m²/día
        }
        if (contienePatron(textoCompleto, "desencofrado")) {
            return 25.0; // m²/día
        }
        
        // ACERO DE REFUERZO
        if (contienePatron(textoCompleto, "acero", "fierro", "armadura", "refuerzo")) {
            return 250.0; // kg/día
        }
        
        // ALBAÑILERÍA
        if (contienePatron(textoCompleto, "muro", "pared", "tabique", "ladrillo", "bloque")) {
            if (contienePatron(textoCompleto, "ladrillo", "bloque")) {
                return 8.0; // m²/día
            }
            return 10.0; // m²/día
        }
        
        // TARRAJEO Y REVESTIMIENTOS
        if (contienePatron(textoCompleto, "tarrajeo", "enlucido", "estuco", "revoque")) {
            if (contienePatron(textoCompleto, "interior")) {
                return 15.0; // m²/día
            }
            if (contienePatron(textoCompleto, "exterior", "fachada")) {
                return 12.0; // m²/día
            }
            return 13.0; // m²/día
        }
        
        // PINTURA
        if (contienePatron(textoCompleto, "pintura", "pintar")) {
            if (contienePatron(textoCompleto, "latex", "vinilo")) {
                return 80.0; // m²/día
            }
            if (contienePatron(textoCompleto, "esmalte", "barniz")) {
                return 60.0; // m²/día
            }
            return 70.0; // m²/día
        }
        
        // INSTALACIONES ELÉCTRICAS
        if (contienePatron(textoCompleto, "electrico", "electrica", "cableado", "tuberia electrica")) {
            if (contienePatron(textoCompleto, "tuberia", "conduit")) {
                return 30.0; // ml/día
            }
            if (contienePatron(textoCompleto, "cableado", "cable")) {
                return 50.0; // ml/día
            }
            return 40.0; // ml/día
        }
        
        // INSTALACIONES SANITARIAS
        if (contienePatron(textoCompleto, "sanitaria", "sanitario", "desague", "agua", "tuberia pvc")) {
            if (contienePatron(textoCompleto, "desague", "alcantarilla")) {
                return 15.0; // ml/día
            }
            if (contienePatron(textoCompleto, "agua", "potable")) {
                return 20.0; // ml/día
            }
            return 18.0; // ml/día
        }
        
        // PISOS Y PAVIMENTOS
        if (contienePatron(textoCompleto, "piso", "pavimento", "ceramica", "porcelanato", "vinilico")) {
            if (contienePatron(textoCompleto, "ceramica", "porcelanato", "mayolica")) {
                return 12.0; // m²/día
            }
            if (contienePatron(textoCompleto, "vinilico", "vinyl")) {
                return 20.0; // m²/día
            }
            return 15.0; // m²/día
        }
        
        // CARPINTERÍA
        if (contienePatron(textoCompleto, "puerta", "ventana", "carpinteria")) {
            return 3.0; // und/día
        }
        
        // TECHADO
        if (contienePatron(textoCompleto, "techo", "cobertura", "tejado", "calamina")) {
            return 25.0; // m²/día
        }
        
        // INSTALACIÓN DE APARATOS
        if (contienePatron(textoCompleto, "aparato", "inodoro", "lavatorio", "lavadero")) {
            return 4.0; // und/día
        }
        
        // LIMPIEZA
        if (contienePatron(textoCompleto, "limpieza", "limpiar")) {
            return 100.0; // m²/día
        }
        
        // DEFAULT: Actividad genérica
        return 10.0; // unidades/día
    }

    /**
     * Calcula el factor de complejidad basado en indicadores clave.
     * Valores:
     * - Simple: 1.3 (mayor rendimiento)
     * - Normal: 1.0 (rendimiento estándar)
     * - Complejo: 0.7 (menor rendimiento)
     * - Muy Complejo: 0.5 (rendimiento muy reducido)
     */
    private double determinarFactorComplejidad(String nombreActividad, String descripcion) {
        String textoCompleto = (nombreActividad + " " + descripcion).toLowerCase();
        
        // Indicadores de ALTA complejidad (reducen rendimiento)
        int indicadoresComplejos = 0;
        
        if (contienePatron(textoCompleto, "especial", "especializado", "precision", "detallado")) {
            indicadoresComplejos += 2;
        }
        if (contienePatron(textoCompleto, "altura", "alto", "elevado", "varios niveles")) {
            indicadoresComplejos += 1;
        }
        if (contienePatron(textoCompleto, "complejo", "dificil", "complicado")) {
            indicadoresComplejos += 2;
        }
        if (contienePatron(textoCompleto, "fino", "acabado fino", "arquitectonico")) {
            indicadoresComplejos += 1;
        }
        if (contienePatron(textoCompleto, "manual", "artesanal")) {
            indicadoresComplejos += 1;
        }
        
        // Indicadores de BAJA complejidad (aumentan rendimiento)
        int indicadoresSimples = 0;
        
        if (contienePatron(textoCompleto, "simple", "basico", "estandar")) {
            indicadoresSimples += 2;
        }
        if (contienePatron(textoCompleto, "mecanizado", "automatico", "maquinaria")) {
            indicadoresSimples += 2;
        }
        if (contienePatron(textoCompleto, "rapido", "express")) {
            indicadoresSimples += 1;
        }
        
        // Calcular factor resultante
        int balance = indicadoresSimples - indicadoresComplejos;
        
        if (balance >= 2) {
            return 1.3; // Trabajo simple
        } else if (balance <= -3) {
            return 0.5; // Muy complejo
        } else if (balance <= -1) {
            return 0.7; // Complejo
        } else {
            return 1.0; // Normal
        }
    }

    /**
     * Calcula el tiempo de rendimiento recomendado según la magnitud del trabajo.
     * 
     * @param rendimiento Rendimiento calculado (unidades/período)
     * @param unidad Unidad de medida (m², m³, kg, und, etc.)
     * @return Tiempo recomendado ("DIA", "HORA", "SEMANA")
     */
    public String calcularTiempoRendimiento(Double rendimiento, String unidad) {
        if (unidad == null) {
            unidad = "";
        }
        
        // Para unidades pequeñas o trabajos rápidos
        if (rendimiento >= 50.0 || unidad.equalsIgnoreCase("und")) {
            return "DIA";
        }
        
        // Para trabajos volumétricos medianos
        if (rendimiento >= 10.0 && rendimiento < 50.0) {
            return "DIA";
        }
        
        // Para trabajos lentos o muy especializados
        if (rendimiento < 10.0 && rendimiento >= 1.0) {
            return "DIA";
        }
        
        // Para trabajos muy lentos
        if (rendimiento < 1.0) {
            return "SEMANA";
        }
        
        return "DIA"; // Por defecto
    }

    /**
     * Determina la unidad más apropiada basándose en el tipo de actividad.
     * 
     * @param nombreActividad Nombre de la actividad
     * @param descripcion Descripción de la actividad
     * @return Unidad recomendada (m², m³, kg, und, ml, glb)
     */
    public String determinarUnidad(String nombreActividad, String descripcion) {
        String textoCompleto = (nombreActividad + " " + descripcion).toLowerCase();
        
        // VOLUMÉTRICOS (m³)
        if (contienePatron(textoCompleto, "concreto", "excavacion", "relleno", "vaciado")) {
            return "m³";
        }
        
        // LINEALES (ml)
        if (contienePatron(textoCompleto, "tuberia", "cable", "cableado", "conduit", "zanja")) {
            return "ml";
        }
        
        // PESO (kg)
        if (contienePatron(textoCompleto, "acero", "fierro", "refuerzo", "armadura")) {
            return "kg";
        }
        
        // UNIDADES (und)
        if (contienePatron(textoCompleto, "puerta", "ventana", "aparato", "inodoro", "lavatorio")) {
            return "und";
        }
        
        // ÁREA (m²) - Más común
        if (contienePatron(textoCompleto, "muro", "piso", "techo", "pintura", "tarrajeo", 
                          "ceramica", "encofrado", "pared", "losa")) {
            return "m²";
        }
        
        // GLOBAL (glb) - Para conjuntos
        if (contienePatron(textoCompleto, "instalacion completa", "obra completa", "global")) {
            return "glb";
        }
        
        // Por defecto
        return "und";
    }

    /**
     * Verifica si el texto contiene alguno de los patrones especificados.
     */
    private boolean contienePatron(String texto, String... patrones) {
        for (String patron : patrones) {
            if (texto.contains(patron)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Ajusta el rendimiento basándose en la cantidad de recursos disponibles.
     * Este método puede ser llamado después cuando se agreguen items.
     * 
     * @param rendimientoBase Rendimiento inicial calculado
     * @param cantidadManoObra Número de trabajadores
     * @param cantidadEquipos Número de equipos
     * @return Rendimiento ajustado
     */
    public Double ajustarRendimientoPorRecursos(Double rendimientoBase, int cantidadManoObra, int cantidadEquipos) {
        double factor = 1.0;
        
        // Más mano de obra aumenta rendimiento (pero con rendimientos decrecientes)
        if (cantidadManoObra > 1) {
            factor += Math.min(0.3 * (cantidadManoObra - 1), 1.5);
        }
        
        // Equipos aumentan significativamente el rendimiento
        if (cantidadEquipos > 0) {
            factor += 0.5 * cantidadEquipos;
        }
        
        return Math.round(rendimientoBase * factor * 100.0) / 100.0;
    }

    /**
     * Calcula el rendimiento y proporciona información detallada sobre el cálculo.
     * 
     * @param nombreActividad Nombre de la actividad
     * @param descripcion Descripción de la actividad
     * @return DTO con información completa del rendimiento
     */
    public RendimientoInfoDTO calcularRendimientoConInfo(String nombreActividad, String descripcion) {
        Double rendimiento = calcularRendimiento(nombreActividad, descripcion);
        String unidad = determinarUnidad(nombreActividad, descripcion);
        String tiempoRendimiento = calcularTiempoRendimiento(rendimiento, unidad);
        
        String textoCompleto = (nombreActividad + " " + descripcion).toLowerCase();
        String tipoActividad = identificarTipoActividad(textoCompleto);
        String nivelComplejidad = determinarNivelComplejidadTexto(nombreActividad, descripcion);
        
        String descripcionCalculo = String.format(
            "Rendimiento calculado: %.2f %s/%s. Tipo: %s. Complejidad: %s",
            rendimiento, unidad, tiempoRendimiento, tipoActividad, nivelComplejidad
        );
        
        return new RendimientoInfoDTO(
            rendimiento, 
            unidad, 
            tiempoRendimiento, 
            descripcionCalculo,
            tipoActividad,
            nivelComplejidad
        );
    }

    /**
     * Identifica el tipo general de actividad.
     */
    private String identificarTipoActividad(String textoCompleto) {
        if (contienePatron(textoCompleto, "excavacion", "relleno", "nivelacion", "movimiento de tierras")) {
            return "Movimiento de Tierras";
        }
        if (contienePatron(textoCompleto, "concreto", "vaciado", "losa", "viga", "columna")) {
            return "Obras de Concreto";
        }
        if (contienePatron(textoCompleto, "encofrado", "formaleta")) {
            return "Encofrado";
        }
        if (contienePatron(textoCompleto, "acero", "fierro", "refuerzo")) {
            return "Acero de Refuerzo";
        }
        if (contienePatron(textoCompleto, "muro", "pared", "ladrillo", "albañileria")) {
            return "Albañilería";
        }
        if (contienePatron(textoCompleto, "tarrajeo", "enlucido", "revoque")) {
            return "Revestimientos";
        }
        if (contienePatron(textoCompleto, "pintura", "pintar")) {
            return "Pintura";
        }
        if (contienePatron(textoCompleto, "electrico", "electrica", "cableado")) {
            return "Instalaciones Eléctricas";
        }
        if (contienePatron(textoCompleto, "sanitaria", "sanitario", "agua", "desague")) {
            return "Instalaciones Sanitarias";
        }
        if (contienePatron(textoCompleto, "piso", "ceramica", "porcelanato")) {
            return "Pisos y Acabados";
        }
        if (contienePatron(textoCompleto, "puerta", "ventana", "carpinteria")) {
            return "Carpintería";
        }
        if (contienePatron(textoCompleto, "techo", "cobertura")) {
            return "Techado";
        }
        return "Actividad General";
    }

    /**
     * Determina el nivel de complejidad como texto descriptivo.
     */
    private String determinarNivelComplejidadTexto(String nombreActividad, String descripcion) {
        double factor = determinarFactorComplejidad(nombreActividad, descripcion);
        
        if (factor >= 1.3) {
            return "Simple (rendimiento aumentado)";
        } else if (factor == 1.0) {
            return "Normal (rendimiento estándar)";
        } else if (factor == 0.7) {
            return "Compleja (rendimiento reducido)";
        } else {
            return "Muy Compleja (rendimiento muy reducido)";
        }
    }
}

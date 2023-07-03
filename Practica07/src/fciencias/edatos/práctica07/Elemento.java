
package fciencias.edatos.practica07;
/**
* Implementación de los atributos que contiene un elemento 
* @author Julieta Vargas Gutiérrez 318341845
* @author Reyes Ramos Luz María 318211073
* @version Enero 2021.
* @since Estructuras de Datos 2022-1.
*/

public class Elemento {
    
    /** Nombre del elemento*/
    String nombre;
    
    /** Simbolo del elemento*/
    String simbolo;
    
    /** Masa atómica del elemento */
    Double masa;
    
    /**
     * Constructor de un elemento de la tabla periodica
     * @param nombre del elemento
     * @param simbolo del elemento 
     * @param masa del elemento de un double
     */

    public Elemento(String nombre, String simbolo, Double masa) {
        this.nombre = nombre;
        this.simbolo = simbolo;
        this.masa = masa;
    }
    
    /**
     * Regresa el nombre del elemento
     * @return nombre del elemento
     */

    public String getNombre() {
        return nombre;

    }
    
    /**
     * Regresa el simbolo del elemento
     * @return simbolo del elemento
     */

    public String getSimbolo() {
        return simbolo;

    }
    
    /**
     * Regresa la masa del elemento
     * @return masa del elemento
     */

    public Double getMasa() {
        return masa;

    }

    @Override
    public String toString() {
        return "Nombre: " + this.nombre + "\nSimbolo: " + this.simbolo +  "\nMasa: " + this.masa + "\n";
    }

}

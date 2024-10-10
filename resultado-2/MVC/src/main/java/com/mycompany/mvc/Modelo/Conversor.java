/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.mvc.Modelo;

/**
 * Clase encargada de la lógica de negocio de la aplicación
 * @author JHM by Jorge Herrera Martín
 * version 1.0
 * Created on 13 sept 2024
 */
public class Conversor {

    //ATRIBUTOS
    private final double cambio; //Cantidad de la moneda destino a la cual equivale un euro
    

    
    //CONSTRUCTOR
    public Conversor(double cambio) {
        this.cambio = cambio; 
    }
    
    
    //MÉTODOS
    /**
     * Convierte de euro a una moneda cualquiera
     * @param cantidad Cantidad de euros que queremos pasar a la nueva moneda
     * @param comision
     * @return Cantidad equivalente en la moneda destino
     */
    public double eurosAmoneda(double cantidad, int comision){
        //Primero sacamos el cambio
        double cantidadTotal = ((cantidad * cambio) - (cantidad * cambio * ((double) comision / 100)));
       
        return cantidadTotal;
    }
    
    /**
     * Convierte una moneda cualquiera a euros
     * @param cantidad Cantidad de monedas que queremos pasar a euros
     * @param comision
     * @return Cantidad equivalente de la moneda a euro
     */
    public double monedaAeuro(double cantidad, int comision){
   
        //Sacamos la cantidad cambiada
        
        double cantidadTotal = ((cantidad / cambio) - (cantidad / cambio * ( (double) comision / 100)));
                       
        return cantidadTotal;
    }
}

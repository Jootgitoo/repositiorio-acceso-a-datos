/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.cambio.euro.peseta;



/**
 * 
 * @author JHM by Jorge Herrera Martín
 * 
 * Es un programa que te puede realizar 2 métodos
 *  - Te convierte euros a pesetas
 *  - Te convierte de pesetas a euros
 */
public class CambioEuroPeseta {

    /**
     * Método que transforma una cantidad de euros (que tu le añadas por teclado) a pesetas
     * @param euros Cantidad de euros que te va ha transformar en pesetas
     * @return Te devuelve la cantidad de pesetas a las que corresponden los euros 
     */
    public static double eurosToPeseta(double euros){
        double solucion = euros * 166.386;
        return solucion;
    }
    
    /**
     * Método que transforma una cantidad de pesetas (que tu le añadas por teclado) a euros
     * @param pesetas Cantidad de pesetas que te va ha transformar en euros
     * @return  Te devuelve la cantidad de euros a las q correspondan las pesetas
     */
    public static double pesetaToEuro (double pesetas){
        double solucion = pesetas / 166.386;
        return solucion;
    }
}

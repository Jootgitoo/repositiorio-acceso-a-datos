/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.cambioeuropesetaconmvc.modelo;

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
     */
    public static void eurosToPeseta(double euros){
        double solucion = euros * 166.386;
        System.out.println("Solucion: " +solucion);
    }
    
    /**
     * Método que transforma una cantidad de pesetas (que tu le añadas por teclado) a euros
     * @param pesetas Cantidad de pesetas que te va ha transformar en euros
     */
    public static void pesetaToEuro (double pesetas){
        double solucion = pesetas / 166.386;
        System.out.println("Solucion: " +solucion);  
    }
}

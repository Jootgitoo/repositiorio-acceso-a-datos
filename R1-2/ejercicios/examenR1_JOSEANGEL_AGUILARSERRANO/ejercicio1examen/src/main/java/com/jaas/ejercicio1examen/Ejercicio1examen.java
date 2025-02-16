/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.jaas.ejercicio1examen;

/**
 *
 * @author JAAS by Jose Angel Aguilar Serrano
 */
public class Ejercicio1examen {

    public static void main(String[] args) {
        
        String nombreArchivo="prueba.txt";
        String ruta = "./PRUEBA";
        String rutaCopia = "./Destino";
        
        Modelo m = new Modelo();
        m.busquedaEjercicio1(nombreArchivo, ruta, rutaCopia);
                
    }
}

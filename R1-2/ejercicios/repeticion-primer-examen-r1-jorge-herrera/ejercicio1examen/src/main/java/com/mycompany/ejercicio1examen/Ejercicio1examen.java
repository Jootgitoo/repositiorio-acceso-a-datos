/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.ejercicio1examen;

/**
 *
 * @author JHM by Jorge Herrera Martín
 */
public class Ejercicio1examen {

    public static void main(String[] args) {
        
        String nombreArchivo = "archivoACopiar.txt";
        String ruta = "./pruebaEj1";
        String rutaCopia = "./pruebaEj1/archivoCopiado.txt";
        
        
        Modelo m = new Modelo();
        m.busquedaEjercicio1(nombreArchivo, ruta, rutaCopia);
    }
}

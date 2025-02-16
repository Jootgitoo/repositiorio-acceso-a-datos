/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.jaas.ejercicio2examen;

/**
 *
 * @author JAAS by Jose Angel Aguilar Serrano
 */
public class Ejercicio2examen {

    public static void main(String[] args) {
        Modelo m = new Modelo();
        
        Reforma ref = new Reforma(4, "Renovar fontaneria", "Calle Toledo 47 2A, Ciudad Real", 600.0);
        m.insertarEjercicio2(ref);
        m.muestraEjercicio2(4);
        ref = new Reforma(4, "Renovar fontaneria", "Calle Toledo 47 2A, Ciudad Real", 700.0);
        m.insertarEjercicio2(ref);
        m.muestraEjercicio2(4);
    }
}

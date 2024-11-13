/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.ejercicio2examen;

/**
 *
 * @author JHM by Jorge Herrera Martín
 */
public class Ejercicio2examen {

    public static void main(String[] args) {
        
        Reforma r1 = new Reforma(4, "Renovar fontaneria", "Calle Toledo 47, 2A, Ciudad Real", 700);
        
        Modelo m = new Modelo();
        
        m.insertaEjercicio2(r1);
        m.muestraEjercicio2(4);
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.jaas.ejercicio3examen;

import com.jaas.ejercicio3examen.controlador.ControladorExamen;
import com.jaas.ejercicio3examen.modelo.Examen;
import com.jaas.ejercicio3examen.vista.ExamenVistaTexto;
import com.jaas.ejercicio3examen.vista.InterfazExamen;

/**
 *
 * @author JAAS by Jose Angel Aguilar Serrano
 */
public class Ejercicio3examen {

    public static void main(String[] args) {
        InterfazExamen vista = new ExamenVistaTexto();
        Examen modelo = new Examen("productos");
        
        ControladorExamen controlador = new ControladorExamen(vista, modelo);
        vista.arranca();   
    }
    
}

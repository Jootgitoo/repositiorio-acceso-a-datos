/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.ejerciciosxmlhtml;

import com.mycompany.ejerciciosxmlhtml.modelo.GestionContenidoDom;
import org.w3c.dom.Element;

/**
 *
 * @author JHM by Jorge Herrera Martín
 */
public class EjerciciosXmlHtml {

    public static void main(String[] args) {
        
        GestionContenidoDom gcd = new GestionContenidoDom("Empleados");
        
        Element nodo = gcd.addNodo("Empleado");
        
        //gcd.addNodo("Departamento", nodo);
        
        gcd.addNodoYTexto(nodo, "Salario", "1200");
        
        gcd.mostrarPantalla();
        
        gcd.generarArchivo("./ejercicios/Empleados.xml");
        
        //gcd.cargarArchivoEnMemoria( "./ejercicios/Empleados.xml" );
        
//        System.out.println("===============================");
//        String nodoPrincipal = gcd.getElementPrincipal();
//        System.out.println("Nodo principal: " +nodoPrincipal);
//        System.out.println("===============================");

        
    }
}

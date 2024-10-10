/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

//Paquetes importados
package com.mycompany.accesoaleatorio;

import com.mycompany.accesoaleatorio.modelo.Empleado;
import com.mycompany.accesoaleatorio.modelo.Escritura;
import com.mycompany.accesoaleatorio.modelo.Lectura;

/**
 *
 * @author JHM by Jorge Herrera Martín
 */
public class AccesoAleatorio {

    public static void main(String[] args) {
    
    
        //Creamos un objeto Escritura (hijo de FihceroEmpleados) y le asignamos la ruta donde vamos a crear el archivo
        Escritura modeloE = new Escritura ("archivo_empleados.dat");
        
        //Creamos empleados y los añadimos al archivo
        Empleado empleado1 = new Empleado (1, "García", 2, 2000);
        modeloE.escribirSegunIdentificador(empleado1);
        
        Empleado empleado2 = new Empleado (80, "Pérez", 5, 1300);
        modeloE.escribirSegunIdentificador(empleado2);

        Empleado empleado3 = new Empleado (2, "Robledo", 1, 1600);
        modeloE.escribirSegunIdentificador(empleado3);
        
        Empleado empleado4 = new Empleado (20, "Molina", 1, 1600);
        modeloE.escribirSegunIdentificador(empleado4);
        
        
        Lectura modeloL = new Lectura ("archivo_empleados.dat");
        Empleado empleadoLeido = modeloL.lecturaEmpleado(80);
        System.out.println(empleadoLeido.toString());
        
        
        
        
    }
}

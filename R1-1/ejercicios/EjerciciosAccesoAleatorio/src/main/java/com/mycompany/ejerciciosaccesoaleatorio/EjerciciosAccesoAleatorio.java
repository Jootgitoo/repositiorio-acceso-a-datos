/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.ejerciciosaccesoaleatorio;

import com.mycompany.ejerciciosaccesoaleatorio.modelo.Empleado;
import com.mycompany.ejerciciosaccesoaleatorio.modelo.Escritura;
import com.mycompany.ejerciciosaccesoaleatorio.modelo.Lectura;

/**
 *
 * @author JHM by Jorge Herrera Martín
 */
public class EjerciciosAccesoAleatorio {

    public static void main(String[] args) {
        
        Escritura escritura = new Escritura("ejemplo/empleados.dat");
        Lectura lectura = new Lectura("ejemplo/empleados.dat");
        
//        Empleado e = new Empleado(1L, "Herrera", 1, 1200.56);
//        escritura.escribirEmpleadoFinalArchivo(e);
        
        Empleado e2 = new Empleado(2L, "Martín", 2, 4300.60);
        escritura.escribirSegunIdentificador(e2);
        
        
        lectura.mostrarRegistros();
        

    }
}

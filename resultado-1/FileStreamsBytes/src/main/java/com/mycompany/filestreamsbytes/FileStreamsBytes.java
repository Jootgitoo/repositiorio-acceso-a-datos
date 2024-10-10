/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.filestreamsbytes;

import com.mycompany.filestreamsbytes.modelo.Copia;
import com.mycompany.filestreamsbytes.modelo.Escritura;
import com.mycompany.filestreamsbytes.modelo.Lectura;
import com.mycompany.filestreamsbytes.modelo.objetos.Empleado;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JHM by Jorge Herrera Martín
 */
public class FileStreamsBytes {

    public static void main(String[] args) {

        
        Lectura modeloL = new Lectura ("archivo_con_objetos");
        Escritura modeloE = new Escritura("archivo_con_objetos");  

        Empleado empleado = new Empleado("Fernando", "Ureña", 23, 800);
        modeloE.escribirObjetos(empleado);

        empleado = new Empleado("Ana", "garcia", 30, 1900);
        modeloE.escribirObjetos(empleado);

        empleado = new Empleado("Israel", "Díaz", 31, 2000);
        modeloE.escribirObjetos(empleado);

        ArrayList<Object> empleadosObjeto = modeloL.lecturaObjetos();
        //Es un bucle for-each que funciona en los arrayList
        //Se recorre el array y a cada elemento aplica un println
        empleadosObjeto.forEach(System.out::println);
        
        //En este caso da igual hacer el casteo pueseto que solo le pasas objetos Empleado
        //Este caso es un casting de ArrayList
        ArrayList<Empleado> empleados = (ArrayList<Empleado>) (ArrayList<?>) modeloL.lecturaObjetos();
            
        
    }
}

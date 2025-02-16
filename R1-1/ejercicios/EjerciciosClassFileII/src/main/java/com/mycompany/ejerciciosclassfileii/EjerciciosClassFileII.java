/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.ejerciciosclassfileii;

import com.mycompany.ejerciciosclassfileii.modelo.Archivo;

/**
 *
 * @author JHM by Jorge Herrera Martín
 */
public class EjerciciosClassFileII {

    public static void main(String[] args) {
        Archivo a = new Archivo();
        
        //a.crearArchivo("./ejercicios/archivoCreado.txt");
        //a.crarArchivo("./ejercicios/", "archivoNuevo.txt");
        //a.leerDirectorio("./ejercicios/archivoNuevo.txt");
        //a.borrar("./ejercicios/");
        //a.renombrar("./ejercicios/renombrame.txt", "./ejercicios/renombrado.txt");
        //a.copiar("./ejercicios/copiame.txt", "./ejercicios/ejemplos/copiado.txt");
        a.mover("./ejercicios/mueveme.txt", "./ejercicios/movido.txt");
    }
}

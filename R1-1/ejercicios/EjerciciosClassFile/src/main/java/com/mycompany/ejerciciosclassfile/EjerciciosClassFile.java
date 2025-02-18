/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.ejerciciosclassfile;

import com.mycompany.ejerciciosclassfile.modelo.Archivo;
import com.mycompany.ejerciciosclassfile.modelo.Carpeta;
import java.io.File;

/**
 *
 * @author JHM by Jorge Herrera Martín
 */
public class EjerciciosClassFile {

    public static void main(String[] args) {
        
        Archivo a = new Archivo();
        Carpeta c = new Carpeta();
       
        //a.crearArchivos("./ejercicios", "archivoNuevo4.txt");
        //a.mostrarContenidoDirectorio();
        a.borrar("./ejercicios");
        //a.renombrar("./ejercicios/renombrado.txt");
        //a.copiar();
        //a.mover();
        
        //c.crearCarpeta("./ejercicios/", "carpetaNueva");
    }
}

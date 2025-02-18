/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.ejerciciosclassfile.modelo;

import java.io.File;

/**
 *
 * @author JHM by Jorge Herrera Martín
 * @version 1.0
 * created on 18 feb 2025
 */
public class Carpeta {
    
    public Carpeta(){
        
    }

    /**
     * Creo una nueva carpeta
     * @param rutaConNombre Ruta donde se va a crear la nueva carpeta
     */
    public void crearCarpeta(String rutaConNombre){
        
        File nuevaCarpeta = new File(rutaConNombre);
        
        nuevaCarpeta.mkdir();
    }
    
    public void crearCarpeta(String rutaSinNombre, String nombre){
        
        File nuevaCarpeta = new File(rutaSinNombre, nombre);
        
        nuevaCarpeta.mkdir();
    }
}

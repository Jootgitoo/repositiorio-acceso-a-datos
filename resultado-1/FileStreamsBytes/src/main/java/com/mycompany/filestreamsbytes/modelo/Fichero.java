/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.filestreamsbytes.modelo;

import java.io.File;

/**
 *
 * @author JHM by Jorge Herrera Martín
 * version 1.0
 * Created on 4 oct 2024
 */
public class Fichero {
    //ATRIBUTOS
    private File ruta;

//------------------------------------------------------------------------------
    //CONSTRUCTOR
    public Fichero(String ruta) {
        this.ruta = new File(ruta);
    }
    
//------------------------------------------------------------------------------
    //MÉTODOS
    
    /**
     * Devuelve el atributo ruta
     * 
     * @return String con la ruta del archivo
     */
    public String getRuta() {
        return ruta.getAbsolutePath();
    }

    /**
     * Cambia el valor de ruta
     * 
     * @param ruta Ruta completa al archivo
     */
    public void setRuta(String ruta) {
        this.ruta = new File(ruta);
    }
    
    
    public boolean existeFichero(){
        if (ruta.exists()){
            return true;
        } else {
            return false;
        }
    }
       
}


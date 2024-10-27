/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.ejerciciorepaso1.modelo;

import java.io.File;

/**
 *
 * @author JHM by Jorge Herrera Martín
 * @version 1.0
 * created on 22 oct 2024
 */
public class FicheroProductos {
    
    //Tamaño de datos en bytes
    private final int LONGITUD_LONG = 8;
    private final int LONGITUD_DOUBLE = 8;
    private final int LONGITUD_ENTERO = 4;
    private final int LONGITUD_CHAR = 2;
    
    //Tamaño fijado para la cadena caracteres
    final int CARACTERES_NOMBRE = 10;
    
    
    //Tamaño de cada uno de los campos que forman el registro del empleado
    private final int LONGITUD_IDENTIFICADOR = LONGITUD_LONG;
    private final int LONGITUD_NOMBRE = CARACTERES_NOMBRE * LONGITUD_CHAR;
    private final int LONGITUD_PRECIO = LONGITUD_DOUBLE;
    private final int LONGITUD_STOCK = LONGITUD_ENTERO;
    
    //Tamaño total del registro
    private final int LONGITUD_TOTAL = LONGITUD_IDENTIFICADOR + LONGITUD_NOMBRE + LONGITUD_PRECIO + LONGITUD_STOCK;
    
    private File ruta;
    
//------------------------------------------------------------------------------  
    //CONSTRUCTOR
    public FicheroProductos (String ruta){
        this.ruta = new File(ruta);
    }
    
//_----------------------------------------------------------------------------
    //Métodos

    public int getLONGITUD_LONG() {
        return LONGITUD_LONG;
    }

    public int getLONGITUD_DOUBLE() {
        return LONGITUD_DOUBLE;
    }

    public int getLONGITUD_ENTERO() {
        return LONGITUD_ENTERO;
    }

    public int getLONGITUD_CHAR() {
        return LONGITUD_CHAR;
    }

    public int getCARACTERES_NOMBRE() {
        return CARACTERES_NOMBRE;
    }

    public int getLONGITUD_IDENTIFICADOR() {
        return LONGITUD_IDENTIFICADOR;
    }

    public int getLONGITUD_NOMBRE() {
        return LONGITUD_NOMBRE;
    }

    public int getLONGITUD_PRECIO() {
        return LONGITUD_PRECIO;
    }

    public int getLONGITUD_STOCK() {
        return LONGITUD_STOCK;
    }

    public int getLONGITUD_TOTAL() {
        return LONGITUD_TOTAL;
    }

    public File getRuta() {
        return ruta;
    }

    public void setRuta(File ruta) {
        this.ruta = ruta;
    }
    

   
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.examenr1jorgeherreramartin.modelo;

import java.io.File;

/**
 *
 * @author JHM by Jorge Herrera Martín
 * version 1.0
 * Created on 23 oct 2024
 */
public class FicheroUniversidad {

    
    private final int LONGITUD_LONG = 8;
    private final int LONGITUD_DOUBLE = 8;
    private final int LONGITUD_ENTERO = 4;
    private final int LONGITUD_CHAR = 2;
    
    final int caracteresCarrera = 20;
    final int caracteresCiudad = 10;
    
    
    private final int LONGITUD_IDENTIFICADOR = LONGITUD_ENTERO;
    private final int LONGITUD_CARRERA = caracteresCarrera * LONGITUD_CHAR;
    private final int LONGITUD_CIUDAD = caracteresCiudad * LONGITUD_CHAR;
    private final int LONGITUD_NOTACORTE = LONGITUD_DOUBLE;
    
    private final int LONGITUD_FICHERO = LONGITUD_IDENTIFICADOR + LONGITUD_CARRERA + LONGITUD_CIUDAD + LONGITUD_NOTACORTE;
    
    private File ruta;

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

    public int getCaracteresCarrera() {
        return caracteresCarrera;
    }

    public int getCaracteresCiudad() {
        return caracteresCiudad;
    }

    public int getLONGITUD_IDENTIFICADOR() {
        return LONGITUD_IDENTIFICADOR;
    }

    public int getLONGITUD_CARRERA() {
        return LONGITUD_CARRERA;
    }

    public int getLONGITUD_CIUDAD() {
        return LONGITUD_CIUDAD;
    }

    public int getLONGITUD_NOTACORTE() {
        return LONGITUD_NOTACORTE;
    }

    public int getLONGITUD_FICHERO() {
        return LONGITUD_FICHERO;
    }

    public File getRuta() {
        return ruta;
    }

    public void setRuta(File ruta) {
        this.ruta = ruta;
    }
    
    
    
    
}


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.ejerciciosaccesoaleatorio.modelo;

import java.io.File;

/**
 *
 * @author JHM by Jorge Herrera Martín
 * @version 1.0
 * created on 16 feb 2025
 */
public class FicheroEmpleados {
    
    //ATRIBUTOS
    
    //Tamaño de los datos
    private final int LONGITUD_LONG = 8;
    private final int LONGITUD_DOUBLE = 8;
    private final int LONGITUD_ENTERO = 4;
    private final int LONGITUD_CHAR = 2;
    
    //Tamaño para las cadenas de caracteres
    private final int CARACTERES_APELLIDO = 10;
    
    //Tamaño para los campos
    private final int LONGITUD_IDENTIFICADOR = LONGITUD_LONG;
    private final int LONGITUD_APELLIDO = CARACTERES_APELLIDO * LONGITUD_CHAR;
    private final int LONGITUD_DEPARTAMENTO = LONGITUD_ENTERO;
    private final int LONGITUD_SALARIO = LONGITUD_DOUBLE;
    
    //Tamaño total del registro
    private final int LONGITUD_TOTAL = LONGITUD_IDENTIFICADOR + LONGITUD_APELLIDO + LONGITUD_DEPARTAMENTO + LONGITUD_SALARIO;

    private File ruta;
    
//------------------------------------------------------------------
    //CONSTRUCTOR
    
    public FicheroEmpleados(String ruta){
        this.ruta = new File(ruta);
    }
    
//-------------------------------------------------------------------
    //MÉTODOS

    public int getCARACTERES_APELLIDO() {
        return CARACTERES_APELLIDO;
    }

    public int getLONGITUD_IDENTIFICADOR() {
        return LONGITUD_IDENTIFICADOR;
    }

    public int getLONGITUD_APELLIDO() {
        return LONGITUD_APELLIDO;
    }

    public int getLONGITUD_DEPARTAMENTO() {
        return LONGITUD_DEPARTAMENTO;
    }

    public int getLONGITUD_SALARIO() {
        return LONGITUD_SALARIO;
    }

    public File getRuta() {
        return ruta;
    }
    
    public void setRuta (String ruta){
        this.ruta = new File(ruta);
    }

    public int getLONGITUD_TOTAL() {
        return LONGITUD_TOTAL;
    }
    
    
    
}

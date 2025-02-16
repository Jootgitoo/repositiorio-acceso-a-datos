/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package com.mycompany.ejerciciosclassfile.vista;

import com.mycompany.ejerciciosclassfile.controlador.Controlador;

/**
 *
 * @author JHM by Jorge Herrera Martín
 * @version 1.0
 * created on 15 feb 2025
 */
public interface InterfazVista {
    
    static final String CREAR_ARCHIVO = "Creas un archivo en la ruta pasada";
    static final String MOSTRAR_CONTENIDO_DIRECTORIO = "Muesta el contenido de un directorio";
    static final String BORRAR_ARCHIVOS_CARPETAS = "Borrar todo lo que haya dentro de un directorio";
    static final String RENOMBRAR_FICHERO = "Renombramos un fichero";
    static final String COPIAR_FICHERO = "Copiamos un fichero";
    static final String MOVER_FICHERO = "Movemos un fichero";
    
    void setControlador(Controlador c);
    void arranca();
    void operacionExitosa();
    void escribeResultado();
    
    String getRuta();
    String getNombre();
}

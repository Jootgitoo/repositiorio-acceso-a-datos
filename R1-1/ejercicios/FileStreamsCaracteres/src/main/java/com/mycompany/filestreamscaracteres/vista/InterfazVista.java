/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package com.mycompany.filestreamscaracteres.vista;

import com.mycompany.filestreamscaracteres.controlador.ControladorEscritura;
import com.mycompany.filestreamscaracteres.controlador.ControladorLectura;

/**
 *
 * @author JHM by Jorge Herrera Martín
 * @version 1.0
 * created on 9 nov 2024
 */
public interface InterfazVista {
    
    static final String LEER_CARACTER_A_CARACTER = "Lee un fichero caracter a caracater";
    static final String LEER_ARRAY_CARACTERES = "Lee un fichero caracter a caracter";
    static final String LEER_CARACTERES_BUFFERED_READER = "Leer caracteres con buffered reader";
    static final String ESCRIBIR_CARACTER = "Escribir un caracter en el fichero";
    static final String ESCRIBIR_ARRAY_CARACTERES = "Escribe un array de caracteres en un archivo";
    static final String ESCRIBIR_STREAM_BUFFERED_CARACTERES = "Escrone un buffer de caracteres";
    static final String ESCRIBIR_BUFFERED_PRINT_CARACTERES = "scribe una cadena de caracteres en un archivo utilizando PrintWriter";
    static final String ENCRIPTAR_FICHERO = "Genera una copia encriptada del fichero";
    static final String DESENCRIPTAR_FICHERO =  "Genera una copia DESencriptada del fichero";
    
//----------------------------------------------------------------------    
    void setControladorLectura (ControladorLectura cL);
    void setControladorEscritura (ControladorEscritura cE);
    void escribirResultado (String cadenaTexto);
    void operacionExitosa();
    void limpiarCampos();
    void arranca();
    char leerCaracter();
    boolean leerBoolean();
    char[] leerArrayCaracteres();
    String leerString();
    

}

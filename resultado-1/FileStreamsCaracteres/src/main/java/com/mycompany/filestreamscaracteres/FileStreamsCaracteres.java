/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.filestreamscaracteres;

import com.mycompany.filestreamscaracteres.modelo.Escritura;
import com.mycompany.filestreamscaracteres.modelo.Fichero;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JHM by Jorge Herrera Martín
 */
public class FileStreamsCaracteres {

    public static void main(String[] args) {
        
    //LLAMADA A LOS MÉTODOS ANTES DE SEPARAR LA LECTURA DE LA ESCRITURA    
    /*    try {
            FileStreams modelo = new FileStreams("src/texto");
            har[] lista = {'h','o','l','a'};
            modelo.escribirStreamArrayCaracteres(lista,false);
            modelo.leerStreamBuffered();
            //System.out.println(contenido);
        } catch (IOException ex) {
            Logger.getLogger(FileStreamsCaracteres.class.getName()).log(Level.SEVERE, null, ex);
        }
    */
    
    /*    FileStreams modelo = new FileStreams("src/texto");
        modelo.escribirStreamBufferedCaracteres("Hoy empezamos de nuevo", true);
    */    
        
    /*    FileStreams modelo = new FileStreams("src/texto");
        modelo.escribirBufferedPrintCaracteres("Segundo ejercicio de hoy", true);
    */    
    
    /*    //Tenemos q declarar un fichero del tipo escritura por que estamos creando un hijo que tiene los atributos propios y los del padre
        Escritura fichero = new Escritura("src/texto");
        fichero.escribirCaracter('A', false);
    */
    
        Escritura fichero = new Escritura("src/texto");
        fichero.escribirBufferedPrintCaracteres("Hola", false);
        fichero.encriptarFichero();
        fichero.desencriptarFichero();
    
        
    
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.filestreamsbytes.modelo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JHM by Jorge Herrera Martín
 * version 1.0
 * Created on 4 oct 2024
 */
public class Copia extends Fichero{
    
//ATRIBUTOS
    private File rutaDestino;
    
//------------------------------------------------------------------------------
    //CONSTRUCTOR
    
    public Copia(String ruta, String rutaDestino){
        super(ruta);
        this.rutaDestino = new File(rutaDestino);
    }
    
//------------------------------------------------------------------------------
    //MÉTODOS
    
    /**
     * Método que te copia un archivo a otro
     */
    public void copiarArchivo(){
        
        //Variable que utilizaremos para leer (en este caso un archivo)
        FileInputStream inputStream = null;
        
        //Variable que utilizaremos para escribir (en este caso escribiremos en un archivo)
        FileOutputStream outputStream = null;
        
        try{ 
            
            //Variable para almacenar el numero de bytes almacenados
            int length = 0;
            
            //Inicializo inputStream pasandole la ruta del fichero que vamos a leer
            inputStream = new FileInputStream(getRuta());
            
            //Inicializo outputStream pasandole la ruta del fichero destino que vamos a escribir
            outputStream = new FileOutputStream(this.rutaDestino);
            
            //Creamos un array para almacenar los datos leidos
            byte[] datosTemporales = new byte[1024];
            
            //Leemos hasta 1024 bytes (es lo q cabe en el array) del archivo que le hemos pasado anteriormente
            //El numero de bytes leidos se guardan en length
            length = inputStream.read(datosTemporales);
            
            //Se ejecuta hasta que length sea 0, es decir, mientras hayas datos para leer
            while(length > 0){
                
                //Escribimos en el outputStream lo q haya en datosTemporales, desde el indice 0 hasta length
                //Es decir escribirmos en outputStream todo lo que haya en el array
                outputStream.write(datosTemporales, 0, length);
                length = inputStream.read(datosTemporales);
            }

        }catch (FileNotFoundException ex){
            Logger.getLogger(Escritura.class.getName()).log(Level.SEVERE, null, ex);
            

        }catch (IOException ex){
            Logger.getLogger(Escritura.class.getName()).log(Level.SEVERE, null, ex);
            
        } finally {
            try {
                inputStream.close();
                outputStream.close();
           }catch(IOException ex) {
                Logger.getLogger(Escritura.class.getName()).log(Level.SEVERE, null, ex);
               
           }
        } 
    }
}

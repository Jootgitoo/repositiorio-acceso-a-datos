/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.ejerciciosclassfileii.modelo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author JHM by Jorge Herrera Martín
 * @version 1.0
 * created on 15 feb 2025
 */
public class Archivo {
    
    //ATRIBUTOS
    
//------------------------------------------------------------------------------
    //CONTRUCTOR
    public Archivo(){
        
    }
    
//------------------------------------------------------------------------------    
    //MÉTODOS
    
    /**
     * Crea un archvio
     * @param ruta ruta con /nombre_archivo.txt incluida
     *  - ./ejercicios/archivoNuevo.txt
     */
    public void crearArchivo(String ruta){
        
        try {
            
            Path p = Paths.get(ruta);
            
            Files.createFile(p);
            
        } catch (IOException ex) {
            Logger.getLogger(Archivo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    /**
     * Crea un archivo pasandole la ruta por un lado y el nombre por otro
     * @param ruta Ruta donde se va a encontrar el archivo sin el nombre
     * @param nombreArchivo Nombre del archivo nuevo
     * 
     * - ./ejercicios/", "archivoNuevo.txt"
     */
    public void crarArchivo(String ruta, String nombreArchivo){
        
        try {
            
            Path p = Paths.get(ruta, nombreArchivo);
            
            Files.createFile(p);
            
        } catch (IOException ex) {
            Logger.getLogger(Archivo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    /**
     * Lee un directorio y escribe el contenido de este
     * @param directorio directorio q va a leer
     * 
     * - ./ejercicios
     */
    public void leerDirectorio(String directorio){
        
        Path p = Paths.get(directorio);
        
        if( Files.isDirectory(p) ){ //Si es un directorio...
            try {
            
                System.out.println("===== HE ENCONTRADO UNA CARPETA =====");
                
                //Lo guardo así para poder hacer el for each
                //.list(p) devuelve un Stream, con eso no puedo interactuar
                List<Path> contenidoDirectorio = Files.list(p).collect(Collectors.toList());
                
                for(Path f: contenidoDirectorio){
                    System.out.println("--> " + f.getFileName());
                }
               
            } catch (IOException ex) {
                Logger.getLogger(Archivo.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } else if ( Files.isRegularFile(p) ){ //Si es un archivo...
            
            System.out.println("===== HE ENCONTRAOD UN ARCHIVO =====");
            System.out.println("--> " + p.getFileName());
        }
    }
    
    
    /**
     * Borra el contenido de una carpeta
     *  - Si dentro hay mas carpetas se borra el contenido de esas carpetas y las carpetas
     * @param directorio 
     * 
     * - ./ejercicios/
     */
    public void borrar(String directorio){
        
        Path p = Paths.get(directorio);
        
        if( Files.isDirectory(p) ){ //Si es un directorio...

            try {
            
                List<Path> contenidoDirectorio = Files.list(p).collect(Collectors.toList());

                for(Path f: contenidoDirectorio){
                    borrar(f.toString());
                }
                
                //Files.delete(p);
            } catch (IOException ex) {
                Logger.getLogger(Archivo.class.getName()).log(Level.SEVERE, null, ex);
            }
 
        }else if( Files.isRegularFile(p) ){ //Si es un archivo...
            
            try {
                
                Files.delete(p);
                
            } catch (IOException ex) {
                Logger.getLogger(Archivo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    
    /**
     * Renombro un archivo
     * @param directorioAntiguo ruta del archivo que vamos a cambiar el nombre
     * @param directorioNuevo ruta del archivo con el nombre nuevo
     * 
     * - "./ejercicios/renombrame.txt", "./ejercicios/renombrado.txt"
     */
    public void renombrar(String directorioAntiguo, String directorioNuevo){
        
        try {
            Path pAntiguo = Paths.get(directorioAntiguo);
            Path pNuevo = Paths.get(directorioNuevo);
            
            Files.move(pAntiguo, pNuevo);
            
        } catch (IOException ex) {
            Logger.getLogger(Archivo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    /**
     * Copiamos un archivo
     * @param rutaOrigen Ruta origen del archivo
     * @param rutaDestino Ruta destino del archivo
     * 
     * - "./ejercicios/copiame.txt", "./ejercicios/copiado.txt"
     * - "./ejercicios/copiame.txt", "./ejercicios/ejemplos/copiado.txt"
     */
    public void copiar(String rutaOrigen, String rutaDestino){
        
        try {
            Path pAntiguo = Paths.get(rutaOrigen);
            Path pNuevo = Paths.get(rutaDestino);
            
            Files.copy(pAntiguo, pNuevo);
            
        } catch (IOException ex) {
            Logger.getLogger(Archivo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    /**
     * Muevo un archivo (copio el archivo a la ruta nueva y elimino la ruta antigüa)
     * @param rutaOrigen Ruta actual donde se encuentra el archivo
     * @param rutaDestino Ruta donde se va a mover el archivo
     */
    public void mover(String rutaOrigen, String rutaDestino){
        
        try {
            
            //Llamo al metodo copiar para que copie el archivo a donde lo quiero mover
            copiar(rutaOrigen, rutaDestino);
            
            Path pAntiguo = Paths.get(rutaOrigen);
            
            //Elimino el archivo de la ruta origen
            Files.delete(pAntiguo);
            
            
        } catch (IOException ex) {
            Logger.getLogger(Archivo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}

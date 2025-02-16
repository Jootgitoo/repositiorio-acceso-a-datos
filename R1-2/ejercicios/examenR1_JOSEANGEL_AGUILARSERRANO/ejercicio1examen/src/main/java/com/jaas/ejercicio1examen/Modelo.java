/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.jaas.ejercicio1examen;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JAAS by Jose Angel Aguilar Serrano
 * @version 1.0
 * Created on 13 nov 2024
 */
public class Modelo {

    public Modelo() {
    }
    
    
    
    public void busquedaEjercicio1(String nombreArchivo, String rutaPartida, String rutaCopia){
        File destino = new File(rutaCopia);
        File partida = new File(rutaPartida);
        
        if(!destino.exists()){
            destino.mkdir();
        }
        
        utilidadBusquedaRecursiva(partida, nombreArchivo, destino);
        
        
    }
    
    public void utilidadBusquedaRecursiva(File directorio, String nombreArchivo, File desino){
        File [] contenido = directorio.listFiles();
        for(File f : contenido){
            //System.out.println("Buscando en: "+f.getPath());
            if( f.isDirectory()){
                utilidadBusquedaRecursiva(f,nombreArchivo,desino);
            }else if(f.isFile() && f.getName().equals(nombreArchivo)){
                //System.out.println("He encontrado el archivo: "+f.getName());
                utilidadCopiar(f,desino);

            }
        }
    }
    
    public void utilidadCopiar (File archivoAcopiar, File destino){
        
        String nombre = archivoAcopiar.getName();
        Path p2 = Paths.get(destino.getPath()+File.separator+nombre);
        File archivo = new File(destino.getPath()+File.separator+nombre);
        if(!archivo.exists()){
        
            try {
                Files.copy(archivoAcopiar.toPath(), p2);
            } catch (IOException ex) {
                Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            System.out.println("==============================================");
            System.out.println("El archivo ya existe en la ruta de destino");
            System.out.println("==============================================");
        }
    }
    
}

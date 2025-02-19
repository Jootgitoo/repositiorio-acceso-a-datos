/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.ejercicio1examen;

import java.io.File;

/**
 *
 * @author b15-18m
 */
public class Ejercicio1examen {
    
    private int contador = 0;

    public static void main(String[] args) {
        System.out.println("Hello World!");
    }
    
    
    public static void ejercicio1examen(String nombreArchivo, String rutaPartida, String rutaCopia){
        
        File fRutaPartida = new File(rutaPartida);
        
        //Saco todo lo que hay dentro de rutaPartida
        String[] archivosDeLaRuta = fRutaPartida.list();
        
        for(String archivo: archivosDeLaRuta){
            
            File farchivo = new File(rutaPartida, archivo);
            
            if(farchivo.isDirectory()){
                ejercicio1examen(nombreArchivo, farchivo.getPath(), rutaCopia);
            }
        }
    }
    
    public void utilizadMostrarContenidoDirectorio(String ruta){
        
        File fRuta = new File(ruta);
        
        if(fRuta.isFile()){ //Si es un archivo muestro su nombre y la ruta
            
            System.out.println("=== HE LEIDO UN ARCHIVO ===");
            
            String nombreArchivo = fRuta.getName();
            System.out.println("--> El archivo se llama: " +nombreArchivo);
            
            String rutaRelativaArchivo = fRuta.getPath();
            System.out.println("--> La ruta del archvio es: " +rutaRelativaArchivo);
            
        } else if(fRuta.isDirectory()){ //Si es un directorio muestro el contenido
            
            //Guardo en contenido en un array
            String[] contenidoDirectorio = fRuta.list();
            
            System.out.println("=== HE LEIDO UN DIRECTORIO ===");
            
            //Lo muestro
            for(String s: contenidoDirectorio){
                System.out.println("--> " +s);
            }
            
        }
    }
}

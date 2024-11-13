/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.ejercicio1examen;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JHM by Jorge Herrera Martín
 * version 1.0
 * Created on 13 nov 2024
 */
public class Modelo {
    
    public Modelo(){
        
    }

    public void busquedaEjercicio1(String nombreArchivo, String rutaPartida, String rutaCopia){
        
        File fileRutaCopia = new File(rutaCopia);
        File fileRutaPartida = new File (rutaPartida);
        ArrayList<String> listaContenidoRutaPartida = new ArrayList<String>();

        
        listaContenidoRutaPartida = UtilidadcustomDir(fileRutaPartida); //Hasta aqui bien
        
        //Compruebo si la ruta donde voy a copiar el archivo existe
        if(!fileRutaPartida.exists()){
            fileRutaPartida.mkdir();  
        }
        
        
        for (String s: listaContenidoRutaPartida){
            
            if(s.equals(nombreArchivo)){
                
                //Copio el archivo nombreArchivo en rutaCopia
                copiarArchivo(rutaPartida, rutaCopia, nombreArchivo);
            }
        }
          
    }
    
    /**
     * Escribe una cadena de caracteres en un archivo utilizando BufferedWriter
     * 
     * @param cadena        Cadena de caracteres a escribir en el archivo
     * @param sobreescribe  Si vamos a sobreescribir el archivo o no
     */
    public void utilidadEscribirStreamBufferedCaracteres(String cadena, boolean sobreescribe, File archivo){
        FileWriter ficheroOut = null;
        try {
            ficheroOut = new FileWriter(archivo, sobreescribe);
            BufferedWriter bufferficheroOut = new BufferedWriter(ficheroOut);
            bufferficheroOut.write(cadena);
            bufferficheroOut.newLine(); // Salto de linea
            
            //Guardamos los cambios en el fichero
            bufferficheroOut.flush();
        } catch (IOException ex) {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                ficheroOut.close();
            } catch (IOException ex) {
                Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    } 
    
    /**
     * Extrae el contenido de una carpeta o un directorio
     * @param folderPath Ruta de la carpeta/archivo del que vamos a extraer la informacion
     * @return Devuelve un ArrayList con el contenido de la carpeta o fichero
     */
    public ArrayList<String> UtilidadcustomDir (File folderPath) {
        
        //ArrayList para almacenar la información del fichero
        ArrayList<String> informacion = new ArrayList<>();

        if (folderPath.isDirectory()) { //Si el File pasado por parametro es un directorio entra por aquí
            
            //Añadimos un mensaje de traza al array haciendo saber que estamos dentro de un directorio
            informacion.add("Contenido Directorio: ");
            
            //Añadimos a un array el contenido de folderPath
            //.list --> Devuelve un array de String con el nombre de las carpetas y archivos que contenga el directorio
            String[] infoRaw = folderPath.list();

            //Bucle para añadir la informacion al ArrayList
            for (int i=0; i<infoRaw.length; i++) {
                
                //Creamos un objeto File por cada "cajon" que tiene el array con el nombre que hay en el array
                //Es decir creas un Objeto file con el nombre del fichero o carpeta que se ha almacenado en el array
                File content = new File(infoRaw[i]);
                
                //Cogemos el nombre del fichero o carpeta
                String fileName = content.getName();
                informacion.add(fileName);
            }
        } else if (folderPath.isFile()) { //Si el File pasado por parametro es un archivo entra por aquí
            
            //Mensaje de traza
            informacion.add("Informacion Fichero: ");

            //Guardamos el nombre del fichero
            String fileName = folderPath.getName();
            
            //Guardmos el tamañao del fichero
            String fileSize = Long.toString(folderPath.length());
            informacion.add(fileName);
        }
        
        //Devuelvo el array
        return informacion;
   }
    
    /**
     * Lee el contenido de un archivo carácter a carácter
     * 
     * @return Cadena con el contenido del archivo
     */
    public String UtilidadLeerCaracterACaracter(File file){
        
        FileReader ficheroIn = null;
        StringBuffer texto = new StringBuffer();
        
        try {          
            //Creamos el flujo de lectura:
            ficheroIn = new FileReader(file);
            //Leemos el código ASCII del carácter contenido en el fichero
            int i;
            
            i= ficheroIn.read();
            
            while (i!=-1) {              
                texto.append((char)i);
                i= ficheroIn.read();
            } 
     
        } catch (IOException ex) {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
        
        } finally {
            try {
                ficheroIn.close();
                
            } catch (IOException ex) {
                Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println(texto.toString());
        return texto.toString();
        
    }

    
    /**
     * Creamos un archivo en una ruta determinada por el usuario con la clase NIO
     * @param ruta Ruta donde se va a generar el archivo
     * @param nombreArchivo Nombre del archivo donde se va a generar
     */
    public void UtilidadCrearArchivo(String ruta, String nombreArchivo){
        try {
            //Ruta del archivo a crear
            Path archivo = Paths.get(ruta, nombreArchivo);
            
            //Crear el archivo si no existe
            if (!Files.exists(archivo)){
                Files.createFile(archivo);
                System.out.println("Archivo creado: " +archivo.toString());
                
            } else {
                System.out.println("El archivo ya existe.");
            }
            
        } catch(IOException e) {
            System.out.println(e);
        }
    }
    
    
    /**
     * Método para copiar un archivo utilizando la clase NIO
     * @param rutaAntigua ruta en la que se encuentra el archivo que queremos copiar
     * @param rutaNueva ruta en la q se va a copiar el archivo
     * @param nombreArchivo nombre del archivo que queremos copiar
     */
    public void copiarArchivo (String rutaAntigua, String rutaNueva, String nombreArchivo){
        
        File archivoARnombrar = new File(rutaAntigua, nombreArchivo);
        
        if ( archivoARnombrar.isFile() ){
            
            Path archivoACopiar = Paths.get(rutaAntigua);
            Path archivoCopiado = archivoACopiar.resolveSibling(rutaNueva);
            System.out.println("Archivo copiado");
            try {
                Files.copy(archivoACopiar, archivoCopiado);
            }catch ( IOException e) {
                System.out.println(e);
            }  
        } 
    }
}

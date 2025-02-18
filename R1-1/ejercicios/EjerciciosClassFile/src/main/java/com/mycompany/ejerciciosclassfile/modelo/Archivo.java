/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.ejerciciosclassfile.modelo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JHM by Jorge Herrera Martín
 * @version 1.0
 * created on 15 feb 2025
 */
public class Archivo {
    
    //ATRIBUTOS
    
    
//------------------------------------------------------------------------    
    //CONSTRUCTORES
    public Archivo(){
        
    }
    
    
//-------------------------------------------------------------------------    
    //MÉTODOS
    
    /**
     * Crea un archivo nuevo
     * @param ruta Ruta donde se va a crear el archivo
     * @param nombreArchivo Nombre que va a tener el archivo
     * 
     * - "./ejercicios", "archivoNuevo4.txt"
     */
    public void crearArchivos(String ruta, String nombreArchivo){
        
        File archivoNuevo = null;
        
        try {
            
            //Pasamos la ruta del archivo
            //Pasamos el nombre del archivo
            archivoNuevo = new File(ruta, nombreArchivo);
            
            //Creamos un nuevo fichero(archivo)
            archivoNuevo.createNewFile();
            
        } catch (IOException ex) {
            
            Logger.getLogger(Archivo.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            archivoNuevo = null;
        }
        
    }
    
    
    /**
     * Muestra el contenido de un directorio o el nombre de un archivo
     * - ./ejercicios
     */
    public void mostrarContenidoDirectorio(String ruta){
        
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
    
    
    /**
     * Borra el archivo pasado por parametro
     * Si es una carpeta borra el contenido interno y la carpeta 
     * @param ruta
     * 
     * - ./ejercicios
     */
    public void borrar(String ruta){
        
        File fRuta = new File(ruta);
        
        if(fRuta.isFile()){ //Si la ruta es un archivo se borra
            
            //Si la ruta es un archivo borramos el archivo
            fRuta.delete();
            
        }else if(fRuta.isDirectory()){ //Si es una carpeta
            
            //Extraemos el contenido
            String[] nombreFicheros = fRuta.list();
            
            
            for(int i=0; i<nombreFicheros.length; i++){
                
                //Comprobamos lo que hay dentro de la carpeta
                File aux = new File(ruta, nombreFicheros[i]);
                if(aux.isDirectory()){ //Si hay mas carpetas volvemos a llamar al metodo para borrar los archivos
                    
                    borrar(aux.getPath());
                    
                    //Cuando esta vacia la carpeta se boora
                    aux.delete();
                    
                } else if(aux.isFile()){ //Si es un archivo se borra
                    
                    aux.delete();
                }
            }
        }   
    }
    
    
    /**
     * Renombramos un archivo
     * @param nuevoNombre ruta del archivo con el nombre renombrado
     * 
     * - ./ejercicios/renombrado.txt
     */
    public void renombrar(String nuevoNombre){
        
        Scanner scanner = new Scanner(System.in);
        
        //Indicamos el archivo que queremos cambiar el nombre
        System.out.print("Indica la ruta del archivo que desea renombrar: ");
        String archivo = scanner.nextLine();
        
        File fArchivo = new File(archivo);
        File fNuevoNombre = new File(nuevoNombre);
        
        //Con este metodo lo renombramos
        boolean exito = fArchivo.renameTo(fNuevoNombre);
        
        if (exito) {
            System.out.println("Archivo renombrado exitosamente.");
            
        } else {
          System.out.println("No se pudo renombrar el archivo.");
        }
        
        scanner.close();
    }
    
    
    public void renombrar(String rutaConAntiguoNombre, String rutaConNuevoNombre){
        
        File archivoAntiguo = new File(rutaConAntiguoNombre);
        File archivoNuevo = new File(rutaConNuevoNombre);
        
        boolean exito = archivoAntiguo.renameTo(archivoNuevo);
        
        if(exito){
            System.out.println("Nombre cambiado con exito");
        } else {
            System.out.println("No se ha cambiado el nombre de la carpeta correctamente");
        }
    }

    
    /**
     * Sirve para copiar un archivo
     */
    public void copiar(){
        
        //Para leer un archivo
        InputStream leer = null;
        
        //Para escribir en un archivo
        OutputStream escribir = null;
        
        try {
            
            Scanner scanner = new Scanner(System.in);
            
            System.out.print("Indica la ruta del archivo original: ");
            String rutaOrigen = scanner.nextLine();
            
            System.out.print("Indica la ruta destino: ");
            String rutaDestino = scanner.nextLine();
            
            File fRutaOriginal = new File(rutaOrigen);
            File fRutaDestino = new File(rutaDestino);
            
            //Para poder leer el archivoOriginal
            leer = new FileInputStream(fRutaOriginal);
            
            //Para poder escribir el archivoDestino
            escribir = new FileOutputStream(fRutaDestino);
            
            //Leo el archivo
            byte[] lectura = new byte[1024];
            int bytesLeidos;
            
            while( (bytesLeidos = leer.read(lectura)) > 0){
                //Escribo  lo leido en un archivo nuevo
                escribir.write(lectura, 0, bytesLeidos);
            }
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Archivo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Archivo.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                leer.close();
            } catch (IOException ex) {
                Logger.getLogger(Archivo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
    /**
     * Movemos un archivo de directorio
     *  - Realmente copiamos un archivo de un directorio a otro y borramos el original
     */
    public void mover(){
        
        InputStream leer = null;
        OutputStream escribir = null;
        
        try {
//---------------------------------------------------------------------------
            //IGUAL EL METODO COPIAR
            Scanner scanner = new Scanner(System.in);
            
            System.out.print("Indica la ruta origen: ");
            String rutaOrigen = scanner.nextLine();
            
            System.out.print("Indica la ruta destino: ");
            String rutaDestino = scanner.nextLine();
            
            File fRutaOrigen = new File(rutaOrigen);
            File fRutaDestino = new File(rutaDestino);
            
            leer = new FileInputStream(fRutaOrigen);
            escribir  = new FileOutputStream(fRutaDestino);
            
            //Leo el archivo y lo escribo en el nuevo
            byte[] lectura = new byte[1024];
            int bytesLeidos;
            
            while( (bytesLeidos = leer.read(lectura)) > 0){
                escribir.write(lectura, 0, bytesLeidos);
            }
//---------------------------------------------------------------------------------            
            //IMPORTANTE CERRAR ANTES DE ELIMINAR
            leer.close();
            escribir.close();
            
            //Elimino el archivo
            fRutaOrigen.delete();
            
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Archivo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Archivo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}












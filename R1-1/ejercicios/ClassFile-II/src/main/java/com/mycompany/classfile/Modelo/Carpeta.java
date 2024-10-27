/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.classfile.Modelo;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.util.ArrayList;


/**
 *
 * @author JHM by Jorge Herrera Martín
 * version 1.0
 * Created on 18 sept 2024
 */
public class Carpeta extends ModeloDirectorios {
  private String ruta;
  
  public Carpeta (String rutaInput) {
    super(rutaInput);
    this.ruta = rutaInput;
  }
  
  public Carpeta () {
    super();
  }
  
  //------------------------------------------------>
   // metodos crear carpeta | todas las posibildades =>
  public void crearCarpeta () {
    File newDirectory = new File (this.ruta);
    newDirectory.mkdir();
  }
  
  public void crearCarpeta (String newFolderName) {
    File newDirectory = new File (this.ruta, newFolderName);
    newDirectory.mkdir();
  }
  
  public void crearCarpeta (File mainFolder, String newFolderName) {
    File newDirectory = new File (mainFolder, newFolderName);
    newDirectory.mkdir();
  }
  
  /**
     * Crea una carpeta utilizando la clase NIO
     * @param directorioRaiz Directorio padre
     * @param nombreDirectorio Nombre de la carpeta donde se va a crear
     */
    public void crearCarpeta(Path directorioRaiz, String nombreDirectorio){
        //Creas una nueva carpeta pasandole un directorio raiz, como es un Path hay q convertirlo a un String con el método toString
        crearCarpeta(directorioRaiz.toString()+"\\"+nombreDirectorio);
    }
    
        
        /**
         * Método que pasado el nombre de un directorio te muestra el contenido si es q es un directorio
         * o te muestra el nombre si es q es un fichero
         * 
         * @param nombreDirectorio Nombre que le pasas por parametro del cual va a extraer la informacion
         * @return 
         */
        public ArrayList<String> mostrarContenidoDirectorio (Path nombreDirectorio){
            
            //ArrayList para meter dentro el contenido del directorio que le pasemos por parametro
            ArrayList<String> contenidoDirectorio = new ArrayList<>();

            //Si nombreDirectorio SI es un directorio...
            if ( Files.isDirectory(nombreDirectorio) ){
                
                //Añadimos al array que es un directorio (mensaje para ayudar al usuairo)
                contenidoDirectorio.add("Contenido del directorio: ");

                //En esta fila guardamos en un array los archivos y directorios que contenga nombreDirectorio (pasado por parametro), es decir,
                //el método newDirectoryStream(nombreDirectorio) se mete dentro de nombreDirectorio (por que se lo pasas por parametro) y te devuelve
                //un array con el nombre del archivo o directorio que haya dentro
                try (DirectoryStream<Path> stream = Files.newDirectoryStream(nombreDirectorio)){
                    
                    for (Path directorio : stream){
                        contenidoDirectorio.add(directorio.getFileName().toString());
                    }
                    
                }catch (IOException e){
                    System.out.println(e);
                }
                
                
            //Si nombreDirectorio NO es un directorio
            //isRegularFile es un método que devuelve un boolean que te dice si lo q le pasas por parametro es un fichero 
            } else if ( Files.isRegularFile(nombreDirectorio)) {
                
                contenidoDirectorio.add(nombreDirectorio.getFileName().toString());
                
            }
            return contenidoDirectorio;
        }
        
        
       


  
   // metodo "dir" carpeta | extraer informacio contenido =>
  public ArrayList<String> customDir (File folderPath) {
    ArrayList<String> informacion = new ArrayList<>();
    
    if (folderPath.isDirectory()) {
      informacion.add("Contenido Directorio: ");
      String[] infoRaw = folderPath.list();
      
      for (int i=0; i<infoRaw.length; i++) {
        File content = new File(infoRaw[i]);
        
        String fileName = content.getName();
        informacion.add("Contenido " + (i+1) + ": " + fileName);
      }
    } else if (folderPath.isFile()) {
      informacion.add("Informacion Fichero: ");
      
      String fileName = folderPath.getName();
      String fileSize = Long.toString(folderPath.length());
      informacion.add("Nombre: " + fileName + " | Tamaño: " + fileSize);
    }
    
    return informacion;
  }
  
   // metodo "borrar" | borra fichero o todos los fichero de una carpeta =>
  public void customDelete (File folderPath) {
    if (folderPath.isFile()) {
      folderPath.delete();
      System.out.println("Fichero: " + folderPath.getName() + " borrado con exito.");
    } else if (folderPath.isDirectory()) {
      String[] internalInfo = folderPath.list();
      
      for (int i=0; i<internalInfo.length; i++) {
        File tempData = new File(folderPath, internalInfo[i]);
        
        if (!tempData.isDirectory()) {
          if (tempData.delete()) {
            System.out.println("Fichero: " + tempData.getName() + " borrado con exito.");
          } else {
            System.out.println("No se pudo borrar el archivo " + tempData.getName());
          }
        }
      }
      
      if (folderPath.delete()) {
        System.out.println("Directorio: " + folderPath.getName() + " borrado con exito.");
      } else {
        System.out.println("No se pudo borrar el directorio " + folderPath.getName());
      }
      
      System.out.println("Archivos borrados con exito.");
    }
  }
  
   // metodo "borrar recursivo" | borra todo dentro de una carpeta =>
  public void customDeleteRecursive (File folderPath) {
    if (folderPath.isFile()) {
      folderPath.delete();
      System.out.println("Fichero: " + folderPath.getName() + " borrado con exito.");
    } else if (folderPath.isDirectory()) {
      String[] internalInfo = folderPath.list();
      
      for (int i=0; i < internalInfo.length; i++) {
        File tempData = new File(folderPath, internalInfo[i]);
        
        if (!tempData.isDirectory()) {
          if (tempData.delete()) {
            System.out.println("Fichero: " + tempData.getName() + " borrado con exito.");
          } else {
            System.out.println("No se pudo borrar el archivo " + tempData.getName());
          }
        } else {
          customDeleteRecursive(tempData);
        }
      }
      
      if (folderPath.delete()) {
        System.out.println("Directorio: " + folderPath.getName() + " borrado con exito.");
      } else {
        System.out.println("No se pudo borrar el directorio " + folderPath.getName());
      }
      
      System.out.println("Archivos borrados con exito.");
    }
  }
  
  /**
   * Método que borra o bien un directorio con sus archivos o un propio archivo utilizando la clase NIO
   * @param nombreArchivo nombre del directorio o archivo que vamos a borrar
   */
  public void borrarAlgo( Path nombreArchivo){
      
        //Si el nombre pasado es un directorio...
        if (Files.isDirectory(nombreArchivo)){
          
            //Llamamos al método mostrarContenidoDirectorio que nos devuelve un arrayList con el contenido que haya en el fichero
            ArrayList<String> contenidoDirectorio = mostrarContenidoDirectorio(nombreArchivo);

            //Recorremos el array
            for(int i=0; i<contenidoDirectorio.size(); i++){

                //Y vamos borrando los ficheros que haya en el interior
                File archivoTemporal = new File(contenidoDirectorio.get(i));
                archivoTemporal.delete();
              
            }
            
           try {
              
                //Borramos el archivo
                Files.delete(nombreArchivo);

            }catch (IOException e){
                System.out.println("Error al borrar el archivo: " +e.getMessage());
            }  
            
        //Si no es un directorio comprobamos que sea un archivo  
        } else if ( Files.isRegularFile(nombreArchivo) ){
          
         
            try {
              
                //Borramos el archivo
                Files.delete(nombreArchivo);

            }catch (IOException e){
                System.out.println("Error al borrar el archivo: " +e.getMessage());
            }  
        }  
    }
  


  //------------------------------------------------>
  @Override
  public String getRuta () {
    return this.ruta;
  }
  
  @Override
  public void setRuta (String rutaInput) {
    this.ruta = rutaInput;
  }
}
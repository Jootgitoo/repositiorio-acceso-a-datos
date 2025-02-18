/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.classfile.Modelo;

import java.io.File;
import java.util.ArrayList;


/**
 *
 * @author JHM by Jorge Herrera Martín
 * version 1.0
 * Created on 18 sept 2024
 */


public class Carpeta extends ModeloDirectorios {
    
    //ATRIBUTOS
    private String ruta;
    
//------------------------------------------------------------------------------
    //CONSTRUCTORES

    public Carpeta (String rutaInput) {
        super(rutaInput);
        this.ruta = rutaInput;
    }

    public Carpeta () {
        super();
    }
  
//------------------------------------------------------------------------------
    //MÉTODOS
    
    /**
     * El proposito de este método es crear una carpeta
     */
    public void crearCarpeta () {
        //Creas un objeto File pasandole solo la ruta
        File newDirectory = new File (this.ruta);
        
        //.mkdir() --> crea un directorio/carpeta
        newDirectory.mkdir();
    }
  
    
    /**
     * Crea una carpeta con un nombre pasado por parametro
     * @param newFolderName nombre que va a tener la carpeta que vamos a crear
     */
    public void crearCarpeta (String newFolderName) {
        //Creamos un objeto File con la ruta y con el nombre que va a tener
        File newDirectory = new File (this.ruta, newFolderName);
        
        //.mkdir() --> crea un directorio/carpeta
        newDirectory.mkdir();
    }
    
    
    /**
     * Crea una carpeta dentro de otra carpeta
     * @param mainFolder Carpeta "padre" dentro de esta se creará la nueva carpeta
     * @param newFolderName nombre de la nueva carpeta
     */
    public void crearCarpeta (File mainFolder, String newFolderName) {
        
        //Creas un objeto File pasandole la carpeta padre y el nombre de la nueva carpeta
        File newDirectory = new File (mainFolder, newFolderName);
        
        //.mkdir() --> crea un directorio/carpeta
        newDirectory.mkdir();
    }
  
    
    // metodo "dir" carpeta | extraer informacio contenido =>
    /**
     * Extrae el contenido de una carpeta o un directorio
     * @param folderPath Ruta de la carpeta/archivo del que vamos a extraer la informacion
     * @return Devuelve un ArrayList con el contenido de la carpeta o fichero
     */
    public ArrayList<String> customDir (File folderPath) {
        
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
                informacion.add("Contenido " + (i+1) + ": " + fileName);
            }
        } else if (folderPath.isFile()) { //Si el File pasado por parametro es un archivo entra por aquí
            
            //Mensaje de traza
            informacion.add("Informacion Fichero: ");

            //Guardamos el nombre del fichero
            String fileName = folderPath.getName();
            
            //Guardmos el tamañao del fichero
            String fileSize = Long.toString(folderPath.length());
            informacion.add("Nombre: " + fileName + " | Tamaño: " + fileSize);
        }
        
        //Devuelvo el array
        return informacion;
   }
  
   // metodo "borrar" | borra fichero o todos los fichero de una carpeta =>
    /**
     * Borra un fichero o todo el contenido de la carpeta (depende el parametro)
     * @param folderPath fichero o carpeta que va a borrar
     */
    public void borrarCarpeta (File folderPath) {
        
        if (folderPath.isFile()) { //Si el objeto File pasado por parametro es un fichero
            
            //.delete() --> Borra el fichero
            folderPath.delete();
            
            System.out.println("Fichero: " + folderPath.getName() + " borrado con exito.");
            
        } else if (folderPath.isDirectory()) { //Si el objeto File pasado es un firectorio
            
            //
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
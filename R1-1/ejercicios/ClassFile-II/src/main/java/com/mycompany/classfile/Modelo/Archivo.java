/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.classfile.Modelo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author JHM by Jorge Herrera Martín
 * version 1.0
 * Created on 20 sept 2024
 */
public class Archivo extends ModeloDirectorios {
    private FileInputStream inputStream = null;
    private FileOutputStream outputStream = null;

    private String ruta;
    private String nombre;
  
    public Archivo (String rutaInput, String nombreInput) {
        super(rutaInput);
    
        this.ruta = rutaInput;
        this.nombre = nombreInput;
    }
  
    public Archivo () {
        super();
    }
  
    //------------------------------------------------>
    // metodo crear archivo | indicar ruta y nombre =>
    //Comento el método para poder hacerlo utilizando la clase NIO (método de abajo)
/*  public void crearArchivo (String rutaInput, String nombreInput) {
        File newFile = null;
        try {
          newFile = new File (rutaInput, nombreInput);
          newFile.createNewFile();
    
        } catch (IOException e) {
          e.printStackTrace();
    
        } finally {
          newFile = null;
        }
    }
*/    
  
    /**
     * Creamos un archivo en una ruta determinada por el usuario con la clase NIO
     * @param ruta Ruta donde se va a generar el archivo
     * @param nombreArchivo Nombre del archivo donde se va a generar
     */
    public void crearArchivo(String ruta, String nombreArchivo){
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
  
   // metodo renombrar archivo | indicar ruta, nombre y nuevo nombre =>
    public boolean renombrarArchivo(String nombreBaseInput, String nombreNuevoInput) {
        File archivoOriginal = new File(nombreBaseInput);
        if (archivoOriginal.exists()) {
            
          File archivoRenombrado = new File(archivoOriginal.getParent(), nombreNuevoInput);
          boolean exito = archivoOriginal.renameTo(archivoRenombrado);
          
          if (exito) {
            System.out.println("Archivo renombrado exitosamente.");
            return true;
            
          } else {
            System.out.println("No se pudo renombrar el archivo.");
            return false;
          }
          
        } else {
          System.out.println("El archivo original no existe.");
          return false;
        }
    }
  
    // metodo mover archivo | indicar ruta-origen & ruta-destino =>
    public void moverArchivo (String rutaOrigenInput, String rutaDestinoInput) {
        File archivoBase = new File (rutaOrigenInput);
        File archivoCopiado = new File (rutaDestinoInput);

        copiarArchivo (rutaOrigenInput, rutaDestinoInput);

        if (archivoCopiado.exists()) {
            
          if (archivoBase.delete()) {
            System.out.println("Archivo movido exitosamente.");
          } else {
            System.out.println("No se pudo eliminar el archivo de origen.");
          }
          
        } else {
          System.out.println("Error al mover el archivo: la copia ha fallado.");
        }
    }
  
   // metodo copiar archivo | indicar ruta-origen & ruta-destino =>
    public void copiarArchivo (String rutaOrigenInput, String rutaDestinoInput) {
        File archivoBase = new File (rutaOrigenInput);
        File archivoCopiado = new File (rutaDestinoInput);
    
        try (FileInputStream inputStream = new FileInputStream(archivoBase); FileOutputStream outputStream = new FileOutputStream(archivoCopiado)) {

          byte[] tempData = new byte[1024];
          int length;

          while ((length = inputStream.read(tempData)) > 0) {
            outputStream.write(tempData, 0, length);
          }

          System.out.println("Archivo copiado exitosamente.");
          
        } catch (IOException e) {
          System.out.println("Error al copiar el archivo: " + archivoBase.getName());
        }
    }
    
    

    /**
     * Método que te renombra un archivo utilizando la clase NIO
     * @param ruta Ruta en la que se encuentra el archivo
     * @param nombreViejo Nombre del archivo actual
     * @param nombreNuevo Nombre nuevo que va a tener el archivo
     */
    public void renombrarArchivoNIO(String ruta, String nombreViejo, String nombreNuevo){
        
        File archivoARenombrar = new File(ruta, nombreViejo);
        
        if ( archivoARenombrar.isFile() ){
            
            Path archivoAntiguo = Paths.get(ruta);
            //resolveSibling()este método da la ruta padre del archivo, y a esa ruta, se le añade el nombre nuevo con un /
            Path archivoRenombrado = archivoAntiguo.resolveSibling(nombreNuevo);
            
            try {
                
               Files.move(archivoAntiguo, archivoRenombrado);
                
            }catch (IOException e){
                System.out.println(e);
            }
            
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
            
            try {
                Files.copy(archivoACopiar, archivoCopiado);
            }catch ( IOException e) {
                System.out.println(e);
            }  
        } 
    }
    
    /**
     * Este método te mueve un archivo de ubicación utilizando la clase nio
     * @param rutaAntigua Ruta en la que se encuentra el archivo
     * @param rutaNueva Ruta donde se va a mover el archivo
     * @param nombreArchivo Nombre del archivo que queremos mover
     */    
    public void moverArchivoNIO (String rutaAntigua, String rutaNueva, String nombreArchivo){
        
        //Creamos un objeto file con el nombre del archivo y la ruta
        File moverArchivo = new File(rutaAntigua, nombreArchivo);
        
        //Si el archivo existe..
        if ( moverArchivo.isFile() ){
            
            //Cambiamos la ruta a la nueva
            Path archivoAMover = Paths.get(rutaAntigua);
            Path archivoMovido = archivoAMover.resolveSibling(rutaNueva);
            
            //Lo movemos
            try {
                Files.move(archivoAMover, archivoMovido);
            }catch ( IOException e) {
                System.out.println(e);
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
  
  public String getNombre () {
    return this.nombre;
  }
  
  public void setNombre (String nombreInput) {
    this.nombre = nombreInput;
  }
}
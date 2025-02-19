/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.classfile.Modelo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author JHM by Jorge Herrera Martín
 * version 1.0
 * Created on 20 sept 2024
 */
public class Archivo extends ModeloDirectorios {
    //ATRIBUTOS
    
    private FileInputStream inputStream = null;
    private FileOutputStream outputStream = null;

    private String ruta;
    private String nombre;
    
//------------------------------------------------------------------------------  
    //CONSTRUCTORES
    public Archivo (String rutaInput, String nombreInput) {
        super(rutaInput);

        this.ruta = rutaInput;
        this.nombre = nombreInput;
    }

    public Archivo () {
        super();
    }
  
  //----------------------------------------------------------------------------
    //MÉTODOS
    
    /**
     * Método que te crea un fichero/archivo
     * @param rutaInput ruta donde se te va a crear el archivo
     * @param nombreInput nombre con el que se te va a crear el archivo
     */
    public void crearFichero (String rutaInput, String nombreInput) {
        
        //Creas una variable File con el nombre newFile
        File newFile = null;
        
        try {
            
            //Especificas la ruta y el nombre del fichero
            newFile = new File (rutaInput, nombreInput);
            
            //Creas el fichero. Hasta esta línea no creas el fichero
            newFile.createNewFile();
            
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            newFile = null;
        }
    }
  
   // metodo renombrar archivo | indicar ruta, nombre y nuevo nombre =>
    /**
     * Método que te renombra un archvo
     * @param nombreBaseInput nombre actual del fichero
     * @param nombreNuevoInput nuevo nombre del fichero
     * @return devuelve true si se ha renombrado, o false si no se ha renombrado
     */
    public boolean renombrarArchivo(String nombreBaseInput, String nombreNuevoInput) {
        //Crear un objeto File con el nombre del fichero
        File archivoOriginal = new File(nombreBaseInput);
        
        //Compruebas si el fichero existe
        if (archivoOriginal.exists()) {  //Si existe entra por aqui
            
            //Creamos otro objeto file pasandole la mimsa ruta que el fichero que queremos renombrar y el nuevo nombre
            //Estamos creando un fichero con el nuevo nombre exactamente en la misma ruta en la que se encuentra el fichero viejo
            File archivoRenombrado = new File(archivoOriginal.getParent(), nombreNuevoInput);
            
            //exito = true si se ha renombrado con correctamente o exito = false si NO se ha renombrado
            //El metodo renameTo sirve para renombrar un archivo PERO por parametro le tienes que añadir un Objeto file
            //Hay que tener encuenta que archivoRenombrado no es un fichero que se haya creado como tal, simplemente es un Objeto File
            boolean exito = archivoOriginal.renameTo(archivoRenombrado); 
            
            if (exito) { //Si se ha renombrado correctamete
                System.out.println("Archivo renombrado exitosamente.");
                return true;
            } else { //Si no se ha renombrado correctamenete
                System.out.println("No se pudo renombrar el archivo.");
                return false;
            }
            
        } else { //Si no existe entra por aquí
            System.out.println("El archivo original no existe.");
            return false;
        }
    }
    
    /**
     * Mueve un archivo de una ruta a otra
     * @param rutaOrigenInput Ruta origen, donde se encuentra el fichero
     * @param rutaDestinoInput Ruta destino, donde se va a ir el fichero
     */
    public void moverArchivo (String rutaOrigenInput, String rutaDestinoInput) {
        
        //Creas un objeto File en la ruta de origen
        File archivoBase = new File (rutaOrigenInput);
        
        //Creas un objeto File en la ruta de destino
        File archivoCopiado = new File (rutaDestinoInput);

        //Llamamos al método copiar archivo
        copiarArchivo(rutaOrigenInput, rutaDestinoInput);

        
        if (archivoCopiado.exists()) { //Si el archivoCopiado (nuevo archivo) se ha copiado correctamente
            
            if (archivoBase.delete()) { //Si el archivoBase (archivo antiguio) se ha borrado correctamente
                System.out.println("Archivo movido exitosamente.");
                
            } else { //Si no se ha borrado correctamente
                System.out.println("No se pudo eliminar el archivo de origen.");
            }
        } else { //Si no se ha copiado correctamente
            System.out.println("Error al mover el archivo: la copia ha fallado.");
        }
    }
  
   /**
    * Método que copia un archivo de una ruta a otra
    * @param rutaOrigenInput Ruta donde se encuentra el archivo original
    * @param rutaDestinoInput Ruta donde se va a crear la copia del archivo nuevo
    */
    public void copiarArchivo (String rutaOrigenInput, String rutaDestinoInput) {
        //Creas un objeto File en la ruta de origen
        File archivoBase = new File (rutaOrigenInput);
      
        //Creas un objeto File en la ruta de destino
        File archivoCopiado = new File (rutaDestinoInput);

        try (FileInputStream inputStream = new FileInputStream(archivoBase); //Sirve para leer el contenido de archivoBase
                FileOutputStream outputStream = new FileOutputStream(archivoCopiado)) { //Sirve para escribir contenido en archivoCopiado (el nuevo)

            byte[] tempData = new byte[1024]; //Aquí se almacena el conteido del fichero de forma temporal
            int length; //Sirve para contar la cantidad de bytes que se han almacenado

            //Este bucle while se ejecuta mientras se lean bytes del archivo de entrada
            //Este bucle se puede ejecutar hata 1024 bytes, cuando ya no tiene nada mas que leer devuelve -1 y en ese caso se saldría del bucle
            //.read lee el archivo entero, el contenido lo va almacenando en un array y devuelve el numero de bytes leidos
            while ((length = inputStream.read(tempData)) > 0) {
                
                //Escribe en el fichero outputStream la informacion que haya en tempData (array) desde el indice 0 (inicio)
                //hasta length(la longitud de esta, es decir, el final del array)
                outputStream.write(tempData, 0, length);
            }

            System.out.println("Archivo copiado exitosamente.");
            
        } catch (IOException e) {
          System.out.println("Error al copiar el archivo: " + archivoBase.getName());
        }
    }
  
  
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
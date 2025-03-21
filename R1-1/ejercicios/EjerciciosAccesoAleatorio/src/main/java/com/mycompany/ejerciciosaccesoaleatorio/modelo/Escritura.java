/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.ejerciciosaccesoaleatorio.modelo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JHM by Jorge Herrera Martín
 * @version 1.0
 * created on 16 feb 2025
 */
public class Escritura extends FicheroEmpleados{
    
    public Escritura(String ruta){
        super(ruta);
    }
    
    
    /**
     * Escribe un empleado al final del archivo
     *  - Calculamos el id según la posicion
     * @param empleado Empleado que va a escribir al final del archivo
     */
    public void escribirEmpleadoFinalArchivo(Empleado empleado){
        
        //Accedo al fichero de forma aleatoria
        RandomAccessFile randomFile = null;
        
        //Posicion en la que voy a colocar el puntero
        long posicion = 0;
        
        //Variable que se utilizará para almacenar el apellido del empleado
        StringBuffer bufferStr = null;
        
        try {

            randomFile = new RandomAccessFile( getRuta(), "rw" );
            
        //BUSCO LA POSICION PARA EMPEZAR A ESCRIBIR
        
            //Si es distinto de 0 es q el fichero tiene contenido
            if(randomFile.length() != 0){
                
                //Me posiciono al final de ese contenido
                posicion = randomFile.length();
            } 
            //Si no entra al if es q el fichero está vacio
            //y se quedaría la posicion en 0
            
        //------------------------------------------------------- 
        
            //Coloco el puntero en la posicion
            randomFile.seek(posicion);
            
            //Con la operacion posicion / super.getLongitud_Total saco el numero de objetos(empleado en este caso) que hay escritos
            // +1 por que el id que le doy es el del ultimo + 1
            randomFile.writeLong( posicion / super.getLONGITUD_TOTAL() + 1 );
            
            //Escribo el apellido
            bufferStr = new StringBuffer(empleado.getApellido());
            bufferStr.setLength(super.getCARACTERES_APELLIDO());
            randomFile.writeChars( bufferStr.toString() );
            
            //Escribo el departamento
            randomFile.writeInt( empleado.getDepartamento() );
            
            //Escribo el salario
            randomFile.writeDouble( empleado.getSalario() );
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Escritura.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Escritura.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            
            try {
                randomFile.close();
            } catch (IOException ex) {
                Logger.getLogger(Escritura.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    
    /**
     * Escribe un empleado en la posicion de su identificador
     *  Si el id es 2 el empleado se inserta en segunda posicion
     * @param empleado Empleado que se va a insertar
     */
    public void escribirSegunIdentificador(Empleado empleado){
        
        RandomAccessFile randomFile = null;
        StringBuffer bufferStr = null;
        
        try {
            
            randomFile = new RandomAccessFile( getRuta(), "rw" );
            
            //Obtengo la posicion
            // - 1 por que el empleado cuyo identificador = 1 se escribe en la pos 0
            // y así sucesivamente
            long posicion = getLONGITUD_TOTAL() * ( empleado.getIdentificador() - 1 );
            
            //Me coloco en la posicion
            randomFile.seek(posicion);
            
            //Escribo al empleado
            randomFile.writeLong( empleado.getIdentificador() );
            
            bufferStr = new StringBuffer( empleado.getApellido() );
            bufferStr.setLength( super.getCARACTERES_APELLIDO() );
            randomFile.writeChars( bufferStr.toString() );
            
            randomFile.writeInt( empleado.getDepartamento() );
            
            randomFile.writeDouble( empleado.getSalario() );

        } catch (FileNotFoundException ex) {
            
            Logger.getLogger(Escritura.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Escritura.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    /**
     * Borra el id del empleado que le pasamos por parametro
     *  - Transforma el id pasado por un 0
     * @param identificador 
     */
    public void borradoLogico(long identificador){
        
        RandomAccessFile randomFile = null;
        
        try {
            
            //Inicializo el fichero
            randomFile = new RandomAccessFile ( getRuta(), "rw" );
            
            long posicion = getLONGITUD_TOTAL() * (identificador - 1);
            
            randomFile.seek(posicion);
            
            randomFile.writeLong(0L);
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Escritura.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Escritura.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
}





















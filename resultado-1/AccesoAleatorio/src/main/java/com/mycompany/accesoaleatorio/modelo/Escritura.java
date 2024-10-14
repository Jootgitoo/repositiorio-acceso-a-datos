/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.accesoaleatorio.modelo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JHM by Jorge Herrera Martín
 * version 1.0
 * Created on 9 oct 2024
 */
public class Escritura extends FicheroEmpleados{

    public Escritura(String ruta) {
        super(ruta);
    }
    
    /**
     * Escribimos al empleado al final del archivo
     * 
     * @param empleado empleado que se va a leer al final del archivo
     */
    public void escribirEmpleadoFinalArchivo (Empleado empleado){
        
        RandomAccessFile randomFile = null;
        
        //Para saber en que posicion hay que empezar a escribir
        long posicion = 0;
        StringBuffer bufferStr = null;
        
        try{
            randomFile = new RandomAccessFile(getRuta(), "rw");
            
            if (randomFile.length()!= 0){
                posicion = randomFile.length();
            }
            
            //Este método te lleva a una posicion del fihcero la cual vamos a empezar a escribir
            //Es decir, si el fichero está vacio posicion = 0 (inicio del fichero) y apartir de ahí empezamos a escribir 
            //si el fichero tiene contenido posicion = final_contenido_fichero y apartir de ahí empezamos a escribir
            randomFile.seek(posicion);
            
            //EJEMPLO DE CALCULAR EL IDENTIFICADOR DESDE LA POSICION
            //Posicion = 120 --> 40 + 40 + 40
            //Tamaño de registro es = 40 (lo devuelve el método super.getLONGITUD_TOTAL()
            //identificador = 120 / 40 + 1 = 4
            randomFile.writeLong(posicion/super.getLONGITUD_TOTAL() + 1);
            
            //Escribimos el apellido
            bufferStr = new StringBuffer(empleado.getApellido());
            bufferStr.setLength(super.getCARACTERES_APELLIDO());
            randomFile.writeChars(bufferStr.toString());
            
            //Escribimos el número del departamento
            randomFile.writeInt(empleado.getDepartamento());
            
            //Escribimos el salario
            randomFile.writeDouble(empleado.getSalario());
            
        } catch(FileNotFoundException ex){
            Logger.getLogger(Escritura.class.getName()).log(Level.SEVERE, null, ex);

        } catch (IOException ex){
            Logger.getLogger(Escritura.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try{
                randomFile.close();
            } catch (IOException ex) {
                Logger.getLogger(Escritura.class.getName()).log(Level.SEVERE, null, ex);
            }
        }  
    }
    
    /**
     * Ejercicio 1 apliacion FUNCIONA!
     * 
     * El método almacena un empleado en el puesto del identificador que tenga
     * Es decir, si el empleado que se le pase tiene id = 6 se guarda en el puesto 6, si tiene id = 9 se guarda en el puesto 9
     * Si hay un empleado con su mismo id lo machaca
     * 
     * @param empleado 
     */
    public void escribirSegunIdentificador(Empleado empleado){
        
        RandomAccessFile randomFile = null;
        
        //Para saber en que posicion hay que empezar a escribir (en este caso vamos a empezar donde esté el identificador)
        long posicion = getLONGITUD_TOTAL() * ( empleado.getIdentificador() - 1 );
        StringBuffer bufferStr = null;
        
        try {
            
            randomFile = new RandomAccessFile(getRuta(), "rw");
            randomFile.seek(posicion);
            
            //Escribimos el identificador
            randomFile.writeLong(empleado.getIdentificador());
            
            //Escribimos el apellido
            bufferStr = new StringBuffer(empleado.getApellido());
            bufferStr.setLength(super.getCARACTERES_APELLIDO());
            randomFile.writeChars(bufferStr.toString());
            
            //Escribimos el número del departamento
            randomFile.writeInt(empleado.getDepartamento());
            
            //Escribimos el salario
            randomFile.writeDouble(empleado.getSalario());

            
        } catch(FileNotFoundException ex){
            Logger.getLogger(Escritura.class.getName()).log(Level.SEVERE, null, ex);

        } catch (IOException ex){
            Logger.getLogger(Escritura.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try{
                randomFile.close();
            } catch (IOException ex) {
                Logger.getLogger(Escritura.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
    }
    
    
    /**
     * Ejercicio 2 FUNCIONA!
     * 
     * Borrado lógico cambia el id de un empleado y lo transforma en 0
     * 
     * @param identificador 
     */
    public void borradoLogico(int identificador) {
        
        //Objeto para leer y escribir el fichero
        RandomAccessFile randomFile = null;
        
        //Calculamos en que posicion está el registro 
        long posicion = (identificador-1)*getLONGITUD_TOTAL();
        
        try {
            
            //Pasamos la ruta del archivo que queremos modificar
            randomFile = new RandomAccessFile(getRuta(),"rw");
           
            //Colocamos el puntero en la posicion (esta posicion es el id de la persona que vamos a cambiar
            randomFile.seek(posicion);
           
            //Cambio el Id a 0
            randomFile.writeLong(0L);
           
           
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Escritura.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Escritura.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                randomFile.close();
            } catch (IOException ex) {
                Logger.getLogger(Escritura.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    /**
     * Ejercicio 4 Funciona!
     * Modifica el apellido del empleado 
     * @param empleado Empleado del que se va a modificar el apellido
     * @param apellido  Apellido que se le va a añadir
     */
    public void modificarApellido(Empleado empleado, String apellido){
        RandomAccessFile randomFile = null;
        StringBuffer bufferStr = null;
        long posicion=0;
        try {
           
            randomFile = new RandomAccessFile(getRuta(),"rw");
            if(randomFile.length() != 0){
               
                posicion = (empleado.getIdentificador()-1) * super.getLONGITUD_TOTAL();
                randomFile.seek(posicion);
               
                if(empleado.getIdentificador() == randomFile.readLong()){ // Con el readLong() leo el long y se posiciona en apellido
                    // Modificamos el apellido
                    bufferStr = new StringBuffer(apellido);
                    bufferStr.setLength(10);
                    randomFile.writeChars(bufferStr.toString());
                   
                }
               
            }
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
    
    
}

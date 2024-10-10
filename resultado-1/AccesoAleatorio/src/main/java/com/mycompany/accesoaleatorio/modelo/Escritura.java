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
    
    //Vamos a escribir registros
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
    
    //Escribimos según el identificadorr, es decir si se le pasa el id 6 lo escribe donde empezaria el empleado numero 6
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
    
    
}

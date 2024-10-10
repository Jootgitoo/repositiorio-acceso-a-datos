/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


package com.mycompany.accesoaleatorio.modelo;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JHM by Jorge Herrera Martín
 * version 1.0
 * Created on 9 oct 2024
 */
public class Lectura extends FicheroEmpleados{

    public Lectura(String ruta) {
        super(ruta);
    }
   
    /**
     * Devuelve un empleado con el identificador dado
     * 
     * @param identificador Id del empleado que queremos mostrar
     * @return  Empleado con sus datos
     */
    public Empleado lecturaEmpleado(int identificador){
                
        // RamdomAccessFile para acceso a un fichero de forma aleatoria
        RandomAccessFile randomFile = null; 
        int posicion = 0;
        Empleado empleado = new Empleado();
        // Creamos un array de byte para la lectura del apellido.
        // El tamaño en bytes será la longitud del apellido que hemos declarado en la clase FicheroEmpleados
        byte [] cadena = new byte [super.getLONGITUD_APELLIDO()]; 
        
        try {
            
            randomFile = new RandomAccessFile(getRuta(), "rw");
            
            // La posicion se calcula a partir del Id. 
            // Imagina que el Id es 3 y que la longitud del registro es 40 (nos lo da getLONGITUD_TOTAL) => (3-1) * 40 = 80.
            // Los datos del empleado 3 estarían a partir de la posición 80
            posicion = (identificador-1) * super.getLONGITUD_TOTAL();
            
            // Verificamos que el id corresponde a una posición menor que la longitud del archivo
            if (posicion < randomFile.length()) {       
                randomFile.seek(posicion);
                
                // Leemos el identificador del empleado
                empleado.setIdentificador(randomFile.readLong());
                // Leemos el apellido
                randomFile.read(cadena);                               
                empleado.setApellido(new String(cadena));
                // Leemos el número del departamento
                empleado.setDepartamento(randomFile.readInt());
                // Leemos el salario
                empleado.setSalario(randomFile.readDouble());  
            }      
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
        // Devolvemos al empleado
        return empleado;
    }
        
        public LinkedList<Empleado> lecturaTodosEmpleados(String ruta){
            
            //Guardo los empleados
            LinkedList<Empleado> listaEmpleado = new LinkedList<>();
            
            try{
                //Fichero que voy a leer
                BufferedReader ficheroALeer = new BufferedReader( new FileReader(ruta));
                
                for(int i=0; i<ficheroALeer.)
                        
            } catch (FileNotFoundException ex){
                System.out.println(ex);
            }
            
            
            return listaEmpleado;
        }   
}
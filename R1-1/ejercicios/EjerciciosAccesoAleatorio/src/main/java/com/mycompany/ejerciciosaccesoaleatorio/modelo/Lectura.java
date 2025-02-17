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
 * created on 17 feb 2025
 */
public class Lectura extends FicheroEmpleados{
    
    //CONSTRUCTOR
    public Lectura(String ruta) {
        super(ruta);
    }
   
//------------------------------------------------------------------------------
    //Métodos
    

    /**
     * Lee 1 solo empleado 
     * @param identificador Id del empleado que va a leer
     * - EL id es único
     * @return Devuelve el empleado
     */
    public Empleado lecturaEmpleado(int identificador){
        
        Empleado empleado = new Empleado();
        
        RandomAccessFile raf = null;
        
        try {
            
            raf = new RandomAccessFile( getRuta(), "rw" );
            
            int posicion = super.getLONGITUD_TOTAL() * (identificador - 1) ;
            
            //Para leer el apellido
            byte[] cadena = new byte[ super.getLONGITUD_APELLIDO() ];
            
            if( posicion < raf.length() ){
                
                raf.seek(posicion);
                
                //Conseguimos el empleado
                empleado.setIdentificador( raf.readLong() );
                
                raf.read( cadena );
                empleado.setApellido( new String(cadena) );
                
                empleado.setDepartamento( raf.readInt() );
                
                empleado.setSalario( raf.readDouble() );
                
            }
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Lectura.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Lectura.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return empleado;
    }
    
    
    public void mostrarRegistros(){
        
        RandomAccessFile raf = null;
        
        try {
            
            raf = new RandomAccessFile( getRuta(), "r" );
            
            while(raf.getFilePointer() < raf.length()){
                
                System.out.println("===== EMPLEADO =====");
                
                long id = raf.readLong();
                System.out.println("--> Id: " +id);
                
                String apellido = readString( raf, CARACTERES_APELLIDO);
                System.out.println("--> Apellido: " +apellido);
                
                int departamento = raf.readInt();
                System.out.println("--> Departamento: " +departamento);
                
                double salario = raf.readDouble();
                System.out.println("--> Salario: " +salario);
            }
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Lectura.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Lectura.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private String readString (RandomAccessFile inputFile, int inputLength) throws IOException {
        char[] charArray = new char[inputLength];
    
        for (int i = 0; i < inputLength; i++) {
          charArray[i] = inputFile.readChar();
        }

        return new String(charArray).trim();
      }
}

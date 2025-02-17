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
public class Lectura extends FicheroEmpleados{

    //CONSTRUCTOR
    public Lectura(String ruta) {
        super(ruta);
    }
   
//------------------------------------------------------------------------------
    //Métodos
    
    /**
     * Devuelve toda la informacion de un empleado
     * 
     * @param identificador Id del empleado que queremos sacar toda la informacion
     * @return  Empleado devuelve un tipo "Empleado" donde gracias a la clase Empleado podremos acceder a todos sus datos
     */
    public Empleado lecturaEmpleado(int identificador){
                
        // RamdomAccessFile para acceso a un fichero de forma aleatoria
        RandomAccessFile randomFile = null; 
        
        //Para posicionarnos en una posición del fichero
        int posicion = 0;
        
        //Creamos un empleado que será el que luego devolveremos con todos los datos
        Empleado empleado = new Empleado();
        
        // Creamos un array de byte para la lectura del apellido.
        // El tamaño en bytes será la longitud del apellido que hemos declarado en la clase FicheroEmpleados
        byte [] cadena = new byte [super.getLONGITUD_APELLIDO()]; 
        
        try {
            
            //Indicamos a que fichero vamos acceder
            randomFile = new RandomAccessFile(getRuta(), "rw");
            
            // La posicion se calcula a partir del Id. 
            // Imagina que el Id es 3 y que la longitud del registro es 40 (nos lo da getLONGITUD_TOTAL) => (3-1) * 40 = 80.
            // Los datos del empleado 3 estarían a partir de la posición 80
            posicion = (identificador-1) * super.getLONGITUD_TOTAL();
            
            // Verificamos que la posicion en la que nos vamos a colocar esté dentro del archivo
            if (posicion < randomFile.length()) { 
                //Nos colocamos en esa posicion
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
    
    // metodo "readString" | metodo auxliar para leer strings de longitud fija ->
    private String readString (RandomAccessFile inputFile, int inputLength) throws IOException {
        char[] charArray = new char[inputLength];
    
        for (int i = 0; i < inputLength; i++) {
          charArray[i] = inputFile.readChar();
        }

        return new String(charArray).trim();
      }
        
    /**
     * Ejercicio 3 Funciona!
     * 
     * Mostramos todos los registros almacenados
     *
     */
    public void mostrarRegistros (){
        
        
        try (RandomAccessFile file = new RandomAccessFile(getRuta(), "r")) {
            while (file.getFilePointer() < file.length()) {
                
                //Leemos el identificador
                long identificador = file.readLong();
                
                //Guardamos el apellido
                String apellido = readString(file, CARACTERES_APELLIDO);
                
                //Guardamos el departamento
                int departamento = file.readInt();
                
                //Guardamos el salario
                double salario = file.readDouble();

                //Si el id es distinto de 0 quiere decir que el empleado existe
                if (identificador != 0) {
                  System.out.printf("ID: %d, Apellido: %s, Departamento: %d, Salario: %.2f%n",
                  identificador, apellido, departamento, salario);
                }
            }
        } catch (IOException ex){
            System.out.println(ex);
        }
    }
}
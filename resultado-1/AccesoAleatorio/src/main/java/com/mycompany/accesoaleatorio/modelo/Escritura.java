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
    
    //CONSTRUCTOR
    public Escritura(String ruta) {
        super(ruta);
    }
    
//------------------------------------------------------------------------------    
    //MÉTODOS
    
    /**
     * Método que escribe un empleado al final del archivo
     * 
     * @param empleado empleado que se va a leer al final del archivo
     */
    public void escribirEmpleadoFinalArchivo (Empleado empleado){
        
        //Creas la variable RandomAccessFile para acceder al fichero de forma aleatoria
        RandomAccessFile randomFile = null;
        
        
        //Variable para saber en que posicion hay que empezar a escribir
        long posicion = 0;
        
        //Variable que se utilizará para almacenar el apellido del empleado
        StringBuffer bufferStr = null;
        
        try{
            
            //Indicamos el archivo que vamos a modificar
            randomFile //Se lo damos en la variable randomFile puesto que así nos podemos posicionar en la parte del fichero que queramos
                    = new RandomAccessFile //Creamos la instancia RandomAccessFile (puesto que antes solo hemos especificado que sea null)
                    (getRuta(), "rw"); //El archivo que vamos a modificar está en el valor getRuta() y que es de lerctura (r) y escritura (w)
            
            //Si el archivo no está vacio estblecemos la posicion al final del archivo
            if (randomFile.length()!= 0){
                posicion = randomFile.length();
            }
            
            
            //Este método te lleva a la posicion del fihcero la cual vamos a empezar a escribir
            //Es decir, si el fichero está vacio posicion = 0 (inicio del fichero) y apartir de ahí empezamos a escribir 
            //si el fichero tiene contenido posicion = final_contenido_fichero y apartir de ahí empezamos a escribir
            randomFile.seek(posicion);
            
            //EJEMPLO DE CALCULAR EL IDENTIFICADOR DESDE LA POSICION
            //Posicion = 120 --> 40 + 40 + 40
            //Tamaño de registro es = 40 (lo devuelve el método super.getLONGITUD_TOTAL()
            //identificador = 120 / 40 + 1 = 4
            randomFile.writeLong(posicion/super.getLONGITUD_TOTAL() + 1);
            
            //Escribimos el apellido
            bufferStr = new StringBuffer(empleado.getApellido()); //Añadimos a la variable bufferStr el apellido del empleado
            bufferStr.setLength(super.getCARACTERES_APELLIDO()); //Nos aseguramos que el apellido no sea mas grande que el valor determinado
            randomFile.writeChars(bufferStr.toString()); //Escribimos el contenido de bufferStr en el fichero
                       
            randomFile.writeInt(empleado.getDepartamento()); //Escribimos el número del departamento
                        
            randomFile.writeDouble(empleado.getSalario()); //Escribimos el salario
            
        } catch(FileNotFoundException ex){
            Logger.getLogger(Escritura.class.getName()).log(Level.SEVERE, null, ex);

        } catch (IOException ex){
            Logger.getLogger(Escritura.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try{
                //Cerramos el fichero que hemos abierto
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
        
        //Creas la variable RandomAccessFile para acceder al fichero de forma aleatoria
        RandomAccessFile randomFile = null;
        
        //Para saber en que posicion hay que empezar a escribir (en este caso vamos a empezar donde esté el identificador)
        long posicion = getLONGITUD_TOTAL() * ( empleado.getIdentificador() - 1 );
        
        //Variable que se utilizará para almacenar el apellido del empleado
        StringBuffer bufferStr = null;
        
        try {
            
            //Inicializamos la variable RandomAccessFile
            randomFile = //Nombre de la varible
                    new RandomAccessFile(getRuta(), //Le pasamos la ruta que tiene el fichero que queremos modificar
                    "rw"); //Y especificamos que el fichero es de lectura (r) y escritura (w)
            
            randomFile.seek(posicion); //Nos posiconamos justo donde debe de empezar a escribirse el empleado con el Id que le hayamos pasado
            
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
                //Cerramos el fichero
                randomFile.close();
            } catch (IOException ex) {
                Logger.getLogger(Escritura.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
    }
    
    
    /**
     * Ejercicio 2 FUNCIONA!
     * 
     * Borrado lógico: cambia el id de un empleado y lo transforma en 0
     * 
     * @param identificador id del empleado que va a cambiar por 0
     */
    public void borradoLogico(int identificador) {
        
        //Creas la variable RandomAccessFile para acceder al fichero de forma aleatoria
        RandomAccessFile randomFile = null;
        
        //Calculamos en que posicion está el registro, para ello se multiplica la longitud de 1 registro por el identificador
        //ya que este va a indicar cuantos registros va a tener que recorrer
        // Se escribe -1 para que la posición que te de sea justo la ultima del registro y al empezar a escribir empiece justo
        //en la primera posicion del registro número identificador (valor pasado por parametro)
        long posicion = (identificador - 1) * getLONGITUD_TOTAL();
        
        try {
            
             //Inicializamos la variable RandomAccessFile
            randomFile = //Nombre de la varible
                    new RandomAccessFile(getRuta(), //Le pasamos la ruta que tiene el fichero que queremos modificar
                    "rw"); //Y especificamos que el fichero es de lectura (r) y escritura (w)
            
            //Colocamos el puntero en la posicion (esta posicion 0 del id de la persona que vamos a cambiar)
            randomFile.seek(posicion);
           
            //Cambio el Id a 0 (L --> para que esriba un long) 
            randomFile.writeLong(0L);
           
           
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Escritura.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Escritura.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                //Cerramos el fichero
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
        
        //Creamos la instancia para acceder al fichero
        RandomAccessFile randomFile = null;
        
        //Creamos un StringBuffer para el apellido
        StringBuffer bufferStr = null;
        
        //Posicion para sabes donde nos tenemos que colocar a escribir
        long posicion=0;
        
        try {
            
           //Indicamos el fichero que vamos a modificar especificando que es de lectura (r) y escritura (w)
            randomFile = new RandomAccessFile(getRuta(),"rw");
            
            
            if(randomFile.length() != 0){ //Si el archivo NO esta vacio entra por aqui
               
                //Calculamos en que posicion nos tenemos que colocar
                posicion = (empleado.getIdentificador()-1) * super.getLONGITUD_TOTAL();
                
                randomFile.seek(posicion); //Nos colocamos en esa posicion
               
                if(empleado.getIdentificador() == randomFile.readLong()){ //Compruebo que esté en el empleado corecto comparando los dos id, si son iguales estoy en el correcto
                    
                    // StringBuffer que contiene el contenido del apellido
                    bufferStr = new StringBuffer(apellido);
                    
                    //Aseguramos que el apellido tenga una longitud max de 10 para que no se salga del registro
                    bufferStr.setLength(10);
                    
                    //Escribimos el apellido
                    randomFile.writeChars(bufferStr.toString());
                   
                }
               
            }
        } catch (IOException ex) {
            Logger.getLogger(Escritura.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                
                //Cerramos el acceso al fichero
                randomFile.close();
            } catch (IOException ex) {
                Logger.getLogger(Escritura.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}

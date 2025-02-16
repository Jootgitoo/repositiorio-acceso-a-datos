/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.filestreamsbytes.modelo;

import com.mycompany.filestreamsbytes.modelo.objetos.MiObjectOutputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JHM by Jorge Herrera Martín
 * version 1.0
 * Created on 4 oct 2024
 */
public class Escritura extends Fichero{
    
    //CONSTRUCTOR
    public Escritura (String ruta){
        super(ruta);
    }
    
//------------------------------------------------------------------------------    
    //MÉTODOS
    
    /**
     * Escribes datos en un fichero
     */
    public void escribirDatosSimples() {
        
        //Se utilizará para escribir datos en un archivo
        FileOutputStream ficheroOut = null;
        
        //Utilizaremos esta variable para escribir datos en formato binario
        DataOutputStream datosOut = null;

        try {

            //Fichero donde se escribiran los datos
            ficheroOut = new FileOutputStream(getRuta());
            
            //Asociamos datosOut a ficheroOut, es decir,
            //los datosOut que tengamos los vamos a escribir en ficheroOut
            datosOut = new DataOutputStream(ficheroOut);

            //Todos estos datos los escribirmos gracias al metodo .write+ lo q queremos escribrir
            datosOut.writeByte((byte) 123);
            datosOut.writeShort( (short) 1234);
            datosOut.writeInt(1234567);
            datosOut.writeLong(123456789123L);
            datosOut.writeFloat( (float) Math.E);
            datosOut.writeDouble(Math.PI);
            datosOut.writeBoolean(true);
            datosOut.writeChar('A');
            datosOut.writeUTF("Esto es una cadena");
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Escritura.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException e){
            Logger.getLogger(Escritura.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try{
                datosOut.close();
                ficheroOut.close();
                
            }catch (IOException e){
                Logger.getLogger(Escritura.class.getName()).log(Level.SEVERE, null, e);
            }
            
        }
    }
    
    
    /**
     * Escribimos objetos en un fichero
     * @param objeto Le pasamos por parametro el objeto que queremos escribir
     */
    public void escribirObjetos(Object objeto){
        
        //Fichero que vamos a escribir el objeto
        FileOutputStream ficheroOut = null;
        
        //Datos de ese objeto que iremos escribiendo
        ObjectOutputStream datosOut = null;
        
        try{
            
            
            if (super.existeFichero()){ //Si existe el fichero en el que vamos a escribir,
                //Es decir, ya está creado el archivo
                
                //Inicializamos el fichero en el que vamos a escribir
                ficheroOut = new FileOutputStream(getRuta(), true);
                
                //Asociamos el fichero a los datos para que los datos se escriban en el fichero
                datosOut = new MiObjectOutputStream(ficheroOut);
                
            }else { //Si no existe el fichero
                
                //Inicializamos el fichero
                ficheroOut = new FileOutputStream(getRuta());
                
                //Asociamos el fichero a los datos para que los datos se escriban en el fichero
                datosOut = new ObjectOutputStream(ficheroOut);
            }
            
            //Escribirmos el objeto en el fichero
            datosOut.writeObject(objeto);
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Escritura.class.getName()).log(Level.SEVERE, null, ex);    
        } catch (IOException e){
            Logger.getLogger(Escritura.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}

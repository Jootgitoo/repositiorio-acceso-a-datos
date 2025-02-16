/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.filestreamsbytes.modelo;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JHM by Jorge Herrera Martín
 * version 1.0
 * Created on 4 oct 2024
 */
public class Lectura extends Fichero{
    
    //CONSTRUCTOR
    public Lectura(String ruta){
        super(ruta);
    }
//------------------------------------------------------------------------------
    public String lecturaDatosSimple(){
        
        FileInputStream ficheroIn = null;
        DataInputStream datosIn = null;
        StringBuffer texto = new StringBuffer();
        
        try {
            
            ficheroIn = new FileInputStream(getRuta());
            datosIn = new DataInputStream(ficheroIn);
            
            texto.append(datosIn.readByte());
            texto.append(" ");
            
            texto.append(datosIn.readShort());
            texto.append(" ");
            
            texto.append(datosIn.readInt());
            texto.append(" ");
            
            texto.append(datosIn.readInt());
            texto.append(" ");
            
            texto.append(datosIn.readLong());
            texto.append(" ");
            
            texto.append(datosIn.readFloat());
            texto.append(" ");

            texto.append(datosIn.readDouble());
            texto.append(" ");
            
            texto.append(datosIn.readBoolean());
            texto.append(" ");
            
            texto.append(datosIn.readChar());
            texto.append(" ");
            
            texto.append(datosIn.readUTF());
            texto.append(" ");
         
            
        }catch  (FileNotFoundException ex) {
            Logger.getLogger(Escritura.class.getName()).log(Level.SEVERE, null, ex);
        }catch (IOException ex){
            Logger.getLogger(Escritura.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            try{
                datosIn.close();
                ficheroIn.close();
                
            }catch (IOException ex){
                Logger.getLogger(Escritura.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return texto.toString();
    }
    
    
    public ArrayList<Object> lecturaObjetos(){
        
        FileInputStream ficheroIn = null;
        ObjectInputStream datosIn = null;
        ArrayList<Object> retornoList = new ArrayList();
        Object retorno = null;
        
        try{
                
            ficheroIn = new FileInputStream(getRuta());
            datosIn = new ObjectInputStream(ficheroIn);
            
            while (ficheroIn.available() > 0){
                //Lee un objeto y lo añade al arrayList
                retorno = datosIn.readObject();
                retornoList.add(retorno);
            }
            
        }catch  (FileNotFoundException ex) {
            Logger.getLogger(Escritura.class.getName()).log(Level.SEVERE, null, ex);           
        }catch (IOException ex){
        Logger.getLogger(Escritura.class.getName()).log(Level.SEVERE, null, ex);
        
        }catch (ClassNotFoundException ex) {
            Logger.getLogger(Escritura.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try{
                ficheroIn.close();
                datosIn.close();
            }catch (IOException ex) {
                Logger.getLogger(Escritura.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return retornoList;
    }
}

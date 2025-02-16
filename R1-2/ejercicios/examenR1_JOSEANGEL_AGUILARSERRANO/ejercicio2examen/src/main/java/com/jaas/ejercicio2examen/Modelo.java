/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.jaas.ejercicio2examen;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JAAS by Jose Angel Aguilar Serrano
 * @version 1.0
 * Created on 13 nov 2024
 */
public class Modelo {

    
    private final int LONGITUD_DOUBLE = 8;
    private final int LONGITUD_INT = 4;
    private final int LONGITUD_CHAR=2;
    
    private final int CARACTERES_STRING =40;
    
    private final int LONGITUD_IDENTIFICADOR = LONGITUD_INT;
    private final int LONGITUD_DESCRIPCION = LONGITUD_CHAR*CARACTERES_STRING;
    private final int LONGITUD_DIRECCION = LONGITUD_CHAR*CARACTERES_STRING;
    private final int LONGITUD_COSTE = LONGITUD_DOUBLE;
    
    private final int LONGITUD_TOTAL=LONGITUD_INT+LONGITUD_DESCRIPCION+LONGITUD_DIRECCION+LONGITUD_COSTE;

    public Modelo() {
    }
    
    
    
    public void insertarEjercicio2(Reforma reforma){
        int id = reforma.getIDReforma();
        String descripcion = reforma.getDescripcion();
        String direccion = reforma.getDireccion();
        double coste = reforma.getCoste();
        
        RandomAccessFile randomFile = null;
        StringBuffer sb = null;
        long posicion = (id-1)*this.LONGITUD_TOTAL;
        
        try {
            randomFile = new RandomAccessFile("./ORIGEN/datosReformas.dat","rw");
            randomFile.seek(posicion);
            if(randomFile.length()!=0){
                
                //Si el identificador que leo es el mismo que le paso como parametro, ya existe
                if(id==randomFile.readInt()){
                    //posiciono el cursor
                    posicion = posicion +this.LONGITUD_INT+ this.LONGITUD_DESCRIPCION+this.LONGITUD_DIRECCION;
                    randomFile.seek(posicion);
                    //cambio el valor de coste
                    randomFile.writeDouble(coste);
                // Si no, el registro no existe, asi que lo escribo al completo
                }
            }else{
                    randomFile.writeInt(id);
                    
                    StringBuffer bufferStr = new StringBuffer(descripcion);
                    bufferStr.setLength(this.CARACTERES_STRING);
              
                    randomFile.writeChars(bufferStr.toString());
                    
                    bufferStr = new StringBuffer(direccion);
                    bufferStr.setLength(this.CARACTERES_STRING);
              
                    randomFile.writeChars(bufferStr.toString());
                    
                    randomFile.writeDouble(coste);
            }
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void muestraEjercicio2(int id){
        
        
        
        RandomAccessFile randomFile = null;
        long posicion = (id-1)*this.LONGITUD_TOTAL;
        try {
            randomFile = new RandomAccessFile("./ORIGEN/datosReformas.dat","r");
            randomFile.seek(posicion);
            
            int idLeido = randomFile.readInt();
            
            byte[] descripcion = new byte[LONGITUD_DESCRIPCION];// Leo la longitud esperada para la cadena EN BYTES
            randomFile.readFully(descripcion);
            String descripcionS = new String(descripcion);
            //Importante esta parte, porque si no, la cadena trae caracteres nulos y XML no acepta eso
            //descripcionS=descripcionS.replace("\0", "");
            
            byte[] direccion = new byte[LONGITUD_DESCRIPCION];// Leo la longitud esperada para la cadena EN BYTES
            randomFile.readFully(direccion);
            String direccionS = new String(direccion);
            //Importante esta parte, porque si no, la cadena trae caracteres nulos y XML no acepta eso
            //direccionS=direccionS.replace("\0", "");
            
            double costeLeido = randomFile.readDouble();
            
            System.out.println("Id: "+idLeido+", Descripcion: "+descripcionS+", Direccion: "+direccionS+", Coste: "+costeLeido);
            
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
        }

        
    }
}

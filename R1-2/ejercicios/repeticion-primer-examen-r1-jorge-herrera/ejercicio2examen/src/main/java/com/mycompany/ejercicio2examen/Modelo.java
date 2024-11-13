/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.ejercicio2examen;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JHM by Jorge Herrera Martín
 * version 1.0
 * Created on 13 nov 2024
 */
public class Modelo {
    
    private final  int LONGITUD_DOUBLE = 8;
    private final  int LONGITUD_INT = 4;
    private final  int LONGITUD_CHAR=2;
    
    private final  int CARACTERES_DESCRIPCION= 30;
    private final  int CARCTERES_DIRECCION= 20;
    
    private final  int LONGITUD_ID = LONGITUD_INT;
    private final  int LONGITUD_DESCRIPCION = CARACTERES_DESCRIPCION * LONGITUD_CHAR;
    private final  int LONGITUD_DIRECCION = CARCTERES_DIRECCION * LONGITUD_CHAR;
    private final  int LONGITUD_COSTE = LONGITUD_DOUBLE;

    
    private final  int LONGITUD_REGISTRO = LONGITUD_ID + LONGITUD_DESCRIPCION + LONGITUD_DIRECCION + LONGITUD_COSTE; ;

    
    public Modelo(){
        
    }
    
    public void insertaEjercicio2(Reforma reforma){
        RandomAccessFile randomFile = null;
        StringBuffer bufferStrCarrera = null;
        StringBuffer bufferStrUniversidad = null;
        
        try {
            
            randomFile = new RandomAccessFile("./ORIGEN/datosReformas.dat", "rw");
            
            boolean encontrado = false;
            
            
            //Miro si esa reforma está hecha o no
            while(!encontrado && randomFile.getFilePointer() < randomFile.length()){
                Reforma r = new Reforma();
                r.setId( randomFile.readInt() );
                
                if (reforma.getId() == r.getId() ){
                    encontrado = true;
                }
                
                byte[] cadenaDes = new byte [LONGITUD_DESCRIPCION];
                randomFile.read(cadenaDes);
                
                byte[] cadenaDir = new byte [LONGITUD_DIRECCION];
                randomFile.read(cadenaDir);
                
                randomFile.readDouble();
            }
            
            int pos = 0;
            if (encontrado == true){
                //Modificar Coste
                
                randomFile.seek(pos);
                
                randomFile.readInt();
                byte[] cadenaDes = new byte [LONGITUD_DESCRIPCION];
                randomFile.read(cadenaDes);
                byte[] cadenaDir = new byte [LONGITUD_DIRECCION];
                randomFile.read(cadenaDir);
                
                randomFile.writeDouble( reforma.getCoste() );
                
            } else {
                pos =( reforma.getId() - 1) * LONGITUD_REGISTRO;
                
                randomFile.writeInt( reforma.getId());
                
                String sDescripcion = reforma.getDescripcion();
                randomFile.writeChars(sDescripcion);
                
                String sDireccion = reforma.getDireccion();
                randomFile.writeChars(sDireccion);
                
                randomFile.writeDouble( reforma.getCoste() );
                
            }
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void muestraEjercicio2(int id){
        
        RandomAccessFile randomFile = null; 
        
        //Para posicionarnos en una posición del fichero
        int posicion = 0;
        
        Reforma r = new Reforma();
        
        byte [] arrayDescripcion = new byte [LONGITUD_DESCRIPCION]; 
        
        try {
            randomFile = new RandomAccessFile("./ORIGEN/datosReformas.dat", "rw");
            posicion = 0;
            
            if (posicion < randomFile.length()){
                
                r.setId( randomFile.readInt());
                
                randomFile.read(arrayDescripcion);
                r.setDescripcion( new String (arrayDescripcion) );
                
                
                byte[] cadenaDir = new byte [LONGITUD_DIRECCION];
                randomFile.read(cadenaDir);
               
                r.setDireccion( new String (cadenaDir)  );
                
                r.setCoste( randomFile.readDouble() );
                
            }
            
            System.out.println("Descripcion: " +r.getDescripcion() + ", Coste: "+ r.getCoste());
            
        } catch (IOException ex) {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
}

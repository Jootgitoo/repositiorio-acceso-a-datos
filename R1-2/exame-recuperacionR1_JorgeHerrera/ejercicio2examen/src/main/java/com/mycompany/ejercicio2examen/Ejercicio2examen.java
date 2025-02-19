/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.ejercicio2examen;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author b15-18m
 */
public class Ejercicio2examen {

    public static void main(String[] args) {
        
        Ejercicio2examen ej2Ex = new Ejercicio2examen();
        
        Lanzamientos lanzamiento = new Lanzamientos(3, "01/04/2025", "Guayana Francesa", 240);
        Lanzamientos lanzamiento2 = new Lanzamientos(6, "12/04/2025", "Cabo Cañaveral", 360);
        
        //ej2Ex.insertaEjercicio2(lanzamiento);
        //ej2Ex.insertaEjercicio2(lanzamiento2);
        
        ej2Ex.muestraEjercicio2(6);
    }
    
    //TAMAÑO DE LOS DATOS
    private final int LONGITUD_ENTERO = 4;
    private final int LONGITUD_CHAR = 2;
    
    final int CARACTERES_ID = 4;
    final int CARACTERES_FECHA = 10;
    final int CARACTERES_LUGAR_LANZAMIENTO = 40;
    final int CARACTERES_HORAS_VUELOS_ESTIMADAS = 4;

    
    private final int LONGITUD_ID = LONGITUD_ENTERO * CARACTERES_ID;
    private final int LONGITUD_FECHA = LONGITUD_CHAR * CARACTERES_FECHA;
    private final int LONGITUD_LUGAR_LANZAMIENTO = LONGITUD_CHAR * CARACTERES_LUGAR_LANZAMIENTO;
    private final int LONGITUD_HORAS_VUELOS_ESTIMADAS = LONGITUD_ENTERO * CARACTERES_HORAS_VUELOS_ESTIMADAS;

    private final int LONGITUD_TOTAL = LONGITUD_ID + LONGITUD_FECHA + LONGITUD_LUGAR_LANZAMIENTO + LONGITUD_HORAS_VUELOS_ESTIMADAS;

    
    
    public void insertaEjercicio2(Lanzamientos lanzamiento){        
        RandomAccessFile randomFile = null;
        StringBuffer bufferStr1 = null;
        StringBuffer bufferStr2 = null;
        
        try {
            
            randomFile = new RandomAccessFile("./LANZAMIENTOS/datosLanzamientos.dat", "rw");
            
            long posicion = 0;
            
            if(randomFile.length() != 0 ){ //Hay datos en el fichero
                
                //Compruebo si existe o no un lanzamiento con ese id
                
                //Me coloco en la pos donde deberia de estar ese id
                posicion = getLONGITUD_TOTAL() * ( lanzamiento.getId() - 1);
                randomFile.seek(posicion);

                //Si existe el id
                if(posicion < randomFile.length()){ //Si no me salgo del fichero puede ser q haya un empleado con este id
                    
                    if( lanzamiento.getId() == randomFile.readInt() ){ //Si el id ya existe
                    
                        //Modificamos la fecha
                        bufferStr1 = new StringBuffer( lanzamiento.getFecha() );
                        bufferStr1.setLength( getLONGITUD_FECHA() );
                        randomFile.writeChars( bufferStr1.toString() );
                    } else {
                        posicion = randomFile.length();
                                        
                        //Escribo el objeto
                        randomFile.writeInt( lanzamiento.getId() );

                        bufferStr1 = new StringBuffer( lanzamiento.getFecha() );
                        bufferStr1.setLength( getLONGITUD_FECHA() );
                        randomFile.writeChars( bufferStr1.toString() );

                        bufferStr2 =  new StringBuffer (lanzamiento.getLugar_lanzamiento());
                        bufferStr2.setLength( getLONGITUD_LUGAR_LANZAMIENTO() );
                        randomFile.writeChars( bufferStr2.toString() );

                        randomFile.writeInt( lanzamiento.getHoras_vuelos_estimadas() );
                    }
                }
                
            } else { //Si no hay datos en el fichero el lanzamiento
                       
                randomFile.seek(posicion);

                //Escribo el objeto
                randomFile.writeInt( lanzamiento.getId() );

                bufferStr1 = new StringBuffer( lanzamiento.getFecha() );
                bufferStr1.setLength( getLONGITUD_FECHA() );
                randomFile.writeChars( bufferStr1.toString() );

                bufferStr2 =  new StringBuffer (lanzamiento.getLugar_lanzamiento());
                bufferStr2.setLength( getLONGITUD_LUGAR_LANZAMIENTO() );
                randomFile.writeChars( bufferStr2.toString() );

                randomFile.writeInt( lanzamiento.getHoras_vuelos_estimadas() );
            } 
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Ejercicio2examen.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Ejercicio2examen.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    
    public void muestraEjercicio2(int id){
        RandomAccessFile randomFile = null;
        
        int posicion = 0;
        
        Lanzamientos lanzamiento = new Lanzamientos();
        
        byte[] lecturaFecha = new byte [ getLONGITUD_FECHA() ];
        byte[] lecturaLugarLanzamiento = new byte [ getLONGITUD_LUGAR_LANZAMIENTO() ];
        
        try {
            
            randomFile = new RandomAccessFile("./LANZAMIENTOS/datosLanzamientos.dat", "rw");
            
            posicion = (id -1) * getLONGITUD_TOTAL();
            
            if (posicion < randomFile.length()){
                
                posicion = posicion + getLONGITUD_ID() +getLONGITUD_FECHA();
                
                randomFile.seek(posicion);
                
                randomFile.read(lecturaLugarLanzamiento);
                lanzamiento.setLugar_lanzamiento(new String(lecturaLugarLanzamiento) );
                
                lanzamiento.setHoras_vuelos_estimadas( randomFile.readInt() );
                
            }
            
            System.out.println("===== LANZAMIENTO LEIDO =====");
            System.out.println("--> Lugar de lanzamiento: " + lanzamiento.getLugar_lanzamiento());
            System.out.println("--> Horas de vuelo estimadas: " +lanzamiento.getHoras_vuelos_estimadas());
                    
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Ejercicio2examen.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Ejercicio2examen.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    public int getLONGITUD_ENTERO() {
        return LONGITUD_ENTERO;
    }

    public int getLONGITUD_CHAR() {
        return LONGITUD_CHAR;
    }

    public int getCARACTERES_ID() {
        return CARACTERES_ID;
    }

    public int getCARACTERES_FECHA() {
        return CARACTERES_FECHA;
    }

    public int getCARACTERES_LUGAR_LANZAMIENTO() {
        return CARACTERES_LUGAR_LANZAMIENTO;
    }

    public int getCARACTERES_HORAS_VUELOS_ESTIMADAS() {
        return CARACTERES_HORAS_VUELOS_ESTIMADAS;
    }

    public int getLONGITUD_ID() {
        return LONGITUD_ID;
    }

    public int getLONGITUD_FECHA() {
        return LONGITUD_FECHA;
    }

    public int getLONGITUD_LUGAR_LANZAMIENTO() {
        return LONGITUD_LUGAR_LANZAMIENTO;
    }

    public int getLONGITUD_HORAS_VUELOS_ESTIMADAS() {
        return LONGITUD_HORAS_VUELOS_ESTIMADAS;
    }

    public int getLONGITUD_TOTAL() {
        return LONGITUD_TOTAL;
    }
    
    
    
}

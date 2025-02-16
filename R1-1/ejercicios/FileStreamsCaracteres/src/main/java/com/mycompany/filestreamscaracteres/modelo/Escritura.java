/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */



package com.mycompany.filestreamscaracteres.modelo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase para escribir, de diferentes fromas, el contenido de un archivo
 * @author JHM by Jorge Herrera Martín
 * version 1.0
 * Created on 2 oct 2024
 */
public class Escritura extends Fichero{

    public Escritura(String ruta) {
        super(ruta);
    }
    
    /**
     * Escribe un carécter en un archivo si no existe te lo crea
     * 
     * @param caracter Carácter a escribir
     * @param add  Si vamos a sobreescribir el archivo o no
     */
    public void escribirCaracter(char caracter, boolean add){
        
        File ficheroExiste = new File(getRuta());
        
        FileWriter ficheroOut = null;
        
        if ( ficheroExiste.isFile() ){
            
            try {
                ficheroOut = new FileWriter(getRuta(), add);

                ficheroOut.write(caracter);

                ficheroOut.close();

            } catch (IOException ex) {
                Logger.getLogger(Fichero.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    ficheroOut.close();
                } catch (IOException ex) {
                    Logger.getLogger(Fichero.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        } else {
            
            try {
                ficheroOut = new FileWriter(getRuta(), add);
                
                ficheroOut.write(caracter);

                ficheroOut.close();
                
            } catch (IOException e){
                
                System.out.println(e);
            } 
        }   
    }
    
    
    /**
     * Escribe un array de caracteres en un archivo
     * 
     * @param caracteres    Array de caracteres a escribir en el archivo
     * @param sobreescribe  Si vamos a sobreescribir el archivo o no 
     */
    public void escribirArrayCaracteres(char[] caracteres, boolean sobreescribe){
        
        FileWriter ficheroOut = null;
        
        try {
            ficheroOut = new FileWriter(getRuta(), sobreescribe);
            
            ficheroOut.write(caracteres);
            
            ficheroOut.close();
            
        } catch (IOException ex) {
            Logger.getLogger(Fichero.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                ficheroOut.close();
            } catch (IOException ex) {
                Logger.getLogger(Fichero.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    } 
    
    
    /**
     * Escribe una cadena de caracteres en un archivo utilizando BufferedWriter
     * 
     * @param cadena        Cadena de caracteres a escribir en el archivo
     * @param sobreescribe  Si vamos a sobreescribir el archivo o no
     */
    public void escribirStreamBufferedCaracteres(String cadena, boolean sobreescribe){
        
        FileWriter ficheroOut = null;
        
        try {
            
            ficheroOut = new FileWriter(getRuta(), sobreescribe);
            
            BufferedWriter bufferficheroOut = new BufferedWriter(ficheroOut);
            
            bufferficheroOut.write(cadena);
            
            bufferficheroOut.newLine(); // Salto de linea
            
            //Guardamos los cambios en el fichero
            bufferficheroOut.flush();
            
        } catch (IOException ex) {
            Logger.getLogger(Fichero.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                ficheroOut.close();
            } catch (IOException ex) {
                Logger.getLogger(Fichero.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    } 

    
    /**
     * Escribe una cadena de caracteres en un archivo utilizando PrintWriter
     * 
     * @param cadena        adena de caracteres a escribir en el archivo
     * @param sobreescribe  Si vamos a sobreescribir el archivo o no
     */
    public void escribirBufferedPrintCaracteres(String cadena, boolean sobreescribe){
        
        FileWriter ficheroOut = null;
        
        try {
            
            ficheroOut = new FileWriter(getRuta(), sobreescribe);
            
            PrintWriter bufferficheroOut = new PrintWriter(ficheroOut);
            
            bufferficheroOut.println(cadena);
            
            //Guardamos los cambios del fichero
            bufferficheroOut.flush();
        } catch (IOException ex) {
            Logger.getLogger(Fichero.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                ficheroOut.close();
            } catch (IOException ex) {
                Logger.getLogger(Fichero.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    } 
    
    /**
     * Este método va a generar una copia encriptada del fichero
     */
    public void encriptarFichero(){
        
        try {
            
            FileReader ficheroALeer = new FileReader(getRuta());
            
            FileWriter archivoEncriptado = new FileWriter("src/archivoEncriptado");
            
            int caracter;
            
            caracter = ficheroALeer.read();

            while ( caracter != -1){
                
                caracter++;
                
                archivoEncriptado.write( (char) caracter );
                caracter = ficheroALeer.read();

            }

            archivoEncriptado.close();
            ficheroALeer.close();
            
        } catch (IOException e){
            System.out.println(e);
        }  
    }
    
    
    /**
     * Creas un archivo desencriptado
     */
    public void desencriptarFichero(){
        try {
            
            FileReader ficheroALeer = new FileReader("src/archivoEncriptado");
            
            FileWriter archivoDesencriptado = new FileWriter("src/archivoDesencriptado");
            
            int caracter;
            
            caracter = ficheroALeer.read();
            
            while ( caracter != -1){
                caracter--;
                archivoDesencriptado.write( (char) caracter);
            }
            
            ficheroALeer.close();
            archivoDesencriptado.close();

        }catch (IOException e){
            System.out.println(e);
        }
        
    }
       
}
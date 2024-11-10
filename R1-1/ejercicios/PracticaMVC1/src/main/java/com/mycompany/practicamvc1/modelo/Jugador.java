/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.practicamvc1.modelo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JHM by Jorge Herrera Martín
 * @version 1.0
 * created on 9 nov 2024
 */
public class Jugador {
    //ATRIBUTOS
    
    private String nombre;
    private int edad;
    private String equipo;

//-------------------------------------------------------------------------------
    //CONSTRUCTOR
    
    public Jugador(String nombre, int edad, String equipo){
        this.nombre = nombre;
        this.edad = edad;
        this.equipo = equipo;
    }

//------------------------------------------------------------------------------
    //MÉTODOS
/*    public void addJugadorFichero(String rutaFichero, Jugador jugador){
        
        FileOutputStream fileOutput = null;
        ObjectOutputStream datosOutput;
        
        try {
            fileOutput = new FileOutputStream(rutaFichero);
            datosOutput = new MiObjectOutputStream(fileOutout);
            
            if(fileOutput.)
            
            
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Jugador.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    
*/    
    
    
//------------------------------------------------------------------------------
    //MÉTODOS EXTRA...
    
    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public String getEquipo() {
        return equipo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setEquipo(String equipo) {
        this.equipo = equipo;
    }
    
    @Override
    public String toString(){
        return "Futbolista cuyo nombre es: " +this.nombre+ " con " +this.edad+ " años que juega en el equipo " +this.equipo;
    }

    

}

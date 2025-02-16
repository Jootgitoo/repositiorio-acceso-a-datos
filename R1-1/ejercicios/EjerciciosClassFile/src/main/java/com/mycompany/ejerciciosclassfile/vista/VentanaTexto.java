/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.ejerciciosclassfile.vista;

import com.mycompany.ejerciciosclassfile.controlador.Controlador;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JHM by Jorge Herrera Martín
 * @version 1.0
 * created on 15 feb 2025
 */
public class VentanaTexto implements InterfazVista{
    
    //ATRIBUTOS
    private final BufferedReader in = new BufferedReader( new InputStreamReader (System.in));
    private Controlador controlador;
    private String ruta;

//-----------------------------------------------------------------------------------------
    //CONSTRUCTOR
    
    public VentanaTexto(){
        super();
    }
    
//-----------------------------------------------------------------
    //MËTODOS
    private String leerString(){
        try {
            return in.readLine();
        } catch (IOException ex) {
            Logger.getLogger(VentanaTexto.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    private int leerOpcion(){
        try {
            String opcion = in.readLine();
            return Integer.parseInt(opcion);
        } catch (IOException ex) {
            Logger.getLogger(VentanaTexto.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
    
    
    private void mostrarMenu(){
        System.out.println("=============================================");
        System.out.println("1. Creas un archivo en la ruta pasada");
        System.out.println("2. Muesta el contenido de un directorio");
        System.out.println("3. Borrar todo lo que haya dentro de un directorio");
        System.out.println("4. Renombramos un fichero");
        System.out.println("5. Copiamos un fichero");
        System.out.println("6. Movemos un fichero");

    }
    
    
    private void procesarNuevaOpcion(){
        mostrarMenu();
        int opcion;
        opcion = leerOpcion();
        
        switch(opcion){
            case 0:{
                System.out.println("\n");
                System.exit(0);
            }
            case 1:{
                this.controlador.actionPerformed(new ActionEvent(this, opcion, CREAR_ARCHIVO));
            }
        }
    }
    
    
    @Override
    public void setControlador(Controlador c) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void arranca() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void operacionExitosa() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void escribeResultado() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getRuta() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getNombre() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}

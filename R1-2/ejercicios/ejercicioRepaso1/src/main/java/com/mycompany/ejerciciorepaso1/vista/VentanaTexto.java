/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.ejerciciorepaso1.vista;

import com.mycompany.ejerciciorepaso1.controlador.ControladorProducto;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import static java.lang.System.in;

/**
 *
 * @author JHM by Jorge Herrera Martín
 * @version 1.0
 * created on 22 oct 2024
 */
public class VentanaTexto implements InterfazVista{

    private final BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    private ControladorProducto controladorProducto;
    private String ruta;
    
    public VentanaTexto () {
        super();
    }
    
    private String leerString () {
    try {
      return in.readLine();
    } catch (IOException e) {
      System.out.println("ERROR! Introduce correctamente la cadena.");
      return null;
    }
  }
    
    private int leerOpcion () {
        try {
          String opcion = in.readLine();
          return Integer.parseInt(opcion);
        } catch (IOException | NumberFormatException e) {
          opcionInvalida();
          return 0;
        }
    }
    
    
    private void mostrarMenu(){
        System.out.println("INDICA LA OPCION A REALIZAR");
        System.out.println("1. leemos todos los producto que tenga el fichero");
        System.out.println("2. buscamos un producto segun el id que pidas");
        System.out.println("\n 0: salir.");

    }
    
    
    private void procesarNuevaOpcion(){
        
        mostrarMenu();
        
        int opcion;
        opcion = leerOpcion();
        
        switch(opcion){
            case 0 -> {
                System.exit(0);
            }
            case 1 ->{
                this.controladorProducto.actionPerformed(new ActionEvent(this, opcion, LEER_PRODUCTO));
            }
            case 2 -> {
                this.controladorProducto.actionPerformed(new ActionEvent(this, opcion, BUSCAR_PRODUCTO_ID));
            }
            
        }
        procesarNuevaOpcion ();

    }
    
    private void opcionInvalida () {
        System.out.println("ERROR! Opcion Invalida.");
    }

    @Override
    public void setControladorProducto(ControladorProducto cP) {
        this.controladorProducto = cP;
    }
    
    public void arranca () {
        procesarNuevaOpcion ();
    }
    
    @Override
    public void operacionExitosa () {
    System.out.println("Operacion realizada con Exito!");
  }

    @Override
    public String getRuta() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}

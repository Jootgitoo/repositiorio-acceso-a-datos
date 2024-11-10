/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.cambioeuropesetaconmvc.vista;

import com.mycompany.cambioeuropesetaconmvc.controlador.ControladorEuroPeseta;
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
 * created on 9 nov 2024
 */
public class VentanaTexto implements InterfazVista{
    private final BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    private ControladorEuroPeseta controladorEuroPeseta;

    public VentanaTexto(){
        super();
    }
    
    /**
     * Lee una cadena de texto
     * @return  Devuelve la cadena leida
     */
    private String leerString(){
        try {
            return in.readLine();
        } catch (IOException ex) {
            Logger.getLogger(VentanaTexto.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    
    /**
     * Lee un numero
     * @return Devuelve el numero leido
     */
    private int leerOpcion(){
        
        try {
            String opcion = in.readLine();
            return Integer.parseInt(opcion);
        } catch (IOException ex) {
            opcionInvalida();
            return 0;
        }
        
    }
    
    /**
     * Metodo que te dice que la opcion seleccionada es invalida
     */
    private void opcionInvalida(){
        System.out.println("Opcion invalida");
    }
    
    
    
    /**
     * Menú de mi aplicacion
     */
    private void mostrarMenu(){
        System.out.println("INDICA LA OPERACION A REALIZAR");
        System.out.println("1. Cambio de auros a pesetas");
        System.out.println("2. Cambio de pesetas a euros");
        System.out.println("0. Salir");
        
        System.out.print("Numero de la operacion elegida: ");
    }
    
   
    
    private void procesarNuevaOpcion(){
        mostrarMenu();
        int opcion;
        opcion = leerOpcion();
        
        switch (opcion){
            case 0 -> {
                System.exit(0);
            }
            case 1 -> {
                this.controladorEuroPeseta.actionPerformed(new ActionEvent(this, opcion, CAMBIAR_DE_EUROS_A_PESETAS));
            }
            case 2 -> {
                this.controladorEuroPeseta.actionPerformed(new ActionEvent(this, opcion, CAMBAIR_DE_PESETAS_A_EUROS));
            }
        }
        procesarNuevaOpcion();
                
    }
    

    @Override
    public void setControladorCambioEuroPeseta(ControladorEuroPeseta cep) {
        this.controladorEuroPeseta = cep;
    }

    @Override
    public double getEuros() {
        System.out.println("> Introduce los euros que deseas convertir");
        double euros;
        euros = Double.parseDouble(leerString());
        return euros;
    }

    @Override
    public double getPesetas() {
        System.out.println("> Introduce las pesetas que deseas convertir");
        double pesetas;
        pesetas = Double.parseDouble(leerString());
        return pesetas;
    }

    @Override
    public void arranca() {
        procesarNuevaOpcion();
    }

    @Override
    public void operacionExitosa() {
        System.out.println("Operacion exitosa!");
    }

    @Override
    public void limpiarCampos() {
        System.out.println("");
    }

}

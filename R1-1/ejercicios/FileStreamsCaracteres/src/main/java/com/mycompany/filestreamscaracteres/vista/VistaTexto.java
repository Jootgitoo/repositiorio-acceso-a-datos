/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.filestreamscaracteres.vista;

import com.mycompany.filestreamscaracteres.controlador.ControladorEscritura;
import com.mycompany.filestreamscaracteres.controlador.ControladorLectura;
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
public class VistaTexto implements InterfazVista{
    
    private final BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    private ControladorLectura controladorLectura;
    private ControladorEscritura controladorEscritura;
    
    
    public VistaTexto(){
        super();
    }
    
    private int leerOpcion() {
        System.out.println("Opcion a realizar: ");
        try {
            String opcion = in.readLine();
            return Integer.parseInt(opcion);
            
        } catch (IOException ex) {
            Logger.getLogger(VistaTexto.class.getName()).log(Level.SEVERE, null, ex);
            opcionInvalida();
            return 0;
        }
        
    }
    
    private void opcionInvalida(){
        System.out.println("Error! Has seleccionado una opcion invalida" );
    }
    
    private void mostrarMenu () {
        
        System.out.println("Indica la operacion que quieres realizar ");
        System.out.println(" 1. leer caracter a caracter un fichero");
        System.out.println(" 2. lee un array de caracteres");
        System.out.println(" 3. leer caracteres con bufferes reader");
        System.out.println(" 4. Escribir un caracter en el fichero;");
        System.out.println(" 5. Escribe un array de caracteres en un archivo");
        System.out.println(" 6. Escrone un buffer de caracteres");
        System.out.println(" 7. Escribe una cadena de caracteres en un archivo utilizando PrintWriter");
        System.out.println(" 8. Genera una copia encriptada del fichero");
        System.out.println(" 9. Genera una copia encriptada del fichero ");
        System.out.println(" 0: salir.");
    }
    
    private void procesarNuevaOpcion(){
        mostrarMenu();
        int opcion;
        opcion = leerOpcion();
        
        switch(opcion){
            case 1 -> {
                this.controladorLectura.actionPerformed(new ActionEvent(this, opcion, LEER_CARACTER_A_CARACTER));
            }
            case 2 -> {
                this.controladorLectura.actionPerformed(new ActionEvent(this, opcion, LEER_ARRAY_CARACTERES));
            }
            case 3 -> {
                this.controladorLectura.actionPerformed(new ActionEvent(this, opcion, LEER_CARACTERES_BUFFERED_READER));
            }
            case 4 -> {
                this.controladorEscritura.actionPerformed(new ActionEvent(this, opcion, ESCRIBIR_CARACTER));
            }
            case 5 -> {
                this.controladorEscritura.actionPerformed(new ActionEvent(this, opcion, ESCRIBIR_ARRAY_CARACTERES));
            }
            case 6 -> {
                this.controladorEscritura.actionPerformed(new ActionEvent(this, opcion, ESCRIBIR_STREAM_BUFFERED_CARACTERES));
            }
            case 7 -> {
                this.controladorEscritura.actionPerformed(new ActionEvent(this, opcion, ESCRIBIR_BUFFERED_PRINT_CARACTERES));
            }
            case 8 -> {
                this.controladorEscritura.actionPerformed(new ActionEvent(this, opcion, ENCRIPTAR_FICHERO));
            }
            case 9 -> {
                this.controladorEscritura.actionPerformed(new ActionEvent(this, opcion, DESENCRIPTAR_FICHERO));
            }
            
        }
        procesarNuevaOpcion();
    }
    
    @Override
    public char leerCaracter(){
        try {
            System.out.print("> Indica el caracter que desee: ");
            String opcion = in.readLine();
            char opcionChar = opcion.charAt(0);
            return opcionChar;
            
        } catch (IOException ex) {
            Logger.getLogger(VistaTexto.class.getName()).log(Level.SEVERE, null, ex);
            opcionInvalida();
            return 0;
        }
    }
    
    @Override
    public char[] leerArrayCaracteres(){
        
        try {
            System.out.print("> Indica la lista de caracteres que quieres escribir: ");
            String opcion = in.readLine();
            char[] listCaracteres = opcion.toCharArray();
            return listCaracteres;
            
        } catch (IOException ex) {
            Logger.getLogger(VistaTexto.class.getName()).log(Level.SEVERE, null, ex);
            opcionInvalida();
            char[] listCaracteres = {};
            return listCaracteres;
        }
    }
    
    @Override
    public boolean leerBoolean(){
        boolean opcionBool = true;
        try {
            System.out.print("> Indica si desea sobreescribir el archivo: ");
            String opcion = in.readLine();
            opcionBool = Boolean.valueOf(opcion);
        } catch (IOException ex) {
            Logger.getLogger(VistaTexto.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return opcionBool;
    }
    
    
    @Override
    public String leerString(){
        String opcion = null;
        try {
            System.out.println("¿Que desea escribir en el archivo?");
            opcion = in.readLine();
           
        } catch (IOException ex) {
            Logger.getLogger(VistaTexto.class.getName()).log(Level.SEVERE, null, ex);
            opcion = "";
        }
        return opcion;
    }

    @Override
    public void setControladorLectura(ControladorLectura cL) {
        this.controladorLectura = cL;
    }

    @Override
    public void escribirResultado(String cadenaTexto) {
        System.out.println( "\n"+cadenaTexto+"\n"  );
    }

    @Override
    public void operacionExitosa() {
        System.out.println("Operacion realizada con exito!");
    }

    @Override
    public void limpiarCampos() {
        System.out.println("");
    }
    
    @Override
    public void arranca(){
        procesarNuevaOpcion();
    }

    @Override
    public void setControladorEscritura(ControladorEscritura cE) {
        this.controladorEscritura = cE;
    }

}

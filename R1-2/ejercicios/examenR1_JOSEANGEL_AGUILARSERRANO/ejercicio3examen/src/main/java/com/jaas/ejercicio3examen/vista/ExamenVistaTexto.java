/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.jaas.ejercicio3examen.vista;

import com.jaas.ejercicio3examen.controlador.ControladorExamen;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JAAS by Jose Angel Aguilar Serrano
 * @version 1.0
 * Created on 13 nov 2024
 */
public class ExamenVistaTexto implements InterfazExamen{

    private ControladorExamen controlador;
    
    private BufferedReader in = new BufferedReader (new InputStreamReader(System.in));
    
    @Override
    public void arranca() {
        procesaNuevaOperacion();
    }
    
    private void solicitaOperacion() {
        System.out.println("=============================================================");
        System.out.println("0. Salir");
        System.out.println("1. Modificar lista de la compra");
        System.out.print("Introduzca la opcion: ");
    }
    
    private void procesaNuevaOperacion() {
        int operacion;
        solicitaOperacion();
        operacion = leeOpcion();
        switch (operacion) {
            case 0 -> {
                System.out.println("Ejecucion finalizada");
                System.exit(0);
            }
            case 1 -> controlador.actionPerformed(new ActionEvent(this, operacion, MODIFICARLISTA));

//            default -> operacionIncorrecta();
        }
        procesaNuevaOperacion();
    }
    
    private int leeOpcion() {
        String s = null;
        try {
            s = in.readLine();
            return Integer.parseInt(s);
        } catch (IOException | NumberFormatException e) {
            operacionIncorrecta();
            return 0;
        }
    }
    
    private void operacionIncorrecta() {
        System.out.print("Operación incorrecta. ");
        procesaNuevaOperacion();
    }

    @Override
    public void setControladorE(ControladorExamen controlador) {
        this.controlador = controlador;
    }

    @Override
    public String leerProducto() {
        System.out.println("Introduce nombre del producto: ");
        String producto = "NO ESPECIFICADO";
        try {
            producto = in.readLine();
        } catch (IOException ex) {
            Logger.getLogger(ExamenVistaTexto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return producto;
    }

    @Override
    public int leerCantidad() {

        System.out.println("Introduce la cantidad: ");
        int cantidad= 0;
        try {
            cantidad = in.read();
        } catch (IOException ex) {
            Logger.getLogger(ExamenVistaTexto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cantidad;

    }

    @Override
    public String leerSupermercado() {
        System.out.println("Introduce nombre del Supermercado: ");
        String producto = "NO ESPECIFICADO";
        try {
            //Declarando de nuevo in me permite solucionar el error en el que
            //Este paso se lo saltaba y no permitia al usuario introducir el supermercado
            //insertando asi un supermercado vacio en el xml
            in = new BufferedReader (new InputStreamReader(System.in));
            producto = in.readLine();
        } catch (IOException ex) {
            Logger.getLogger(ExamenVistaTexto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return producto;
    }

}

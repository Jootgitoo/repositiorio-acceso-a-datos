/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.filestreamscaracteres.controlador;

import com.mycompany.filestreamscaracteres.modelo.Lectura;
import com.mycompany.filestreamscaracteres.vista.InterfazVista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author JHM by Jorge Herrera Martín
 * @version 1.0
 * created on 9 nov 2024
 */
public class ControladorLectura implements ActionListener{

    private final InterfazVista vista;
    private final Lectura modelo;
    
    public ControladorLectura(InterfazVista vistaInput, Lectura modeloInput){
        this.vista = vistaInput;
        this.modelo = modeloInput;
        this.vista.setControladorLectura (this);
        
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        
        switch (evento.getActionCommand()){
            case InterfazVista.LEER_CARACTER_A_CARACTER -> {
                //Primero variables para guardar lo q necesito (para meterle parametros el metodo)
                    //En este caso no tengo
                
                //Llamo al metodo
                String solucion = this.modelo.leerCaracterACaracter();
                
                //Lo muestro  a la vista
                this.vista.escribirResultado(solucion);
                
                //Métodos para seguir con el programa
                this.vista.operacionExitosa();
                this.vista.limpiarCampos();
                        
            }
            case InterfazVista.LEER_ARRAY_CARACTERES -> {
                
                //Recogida de parametros
                //no tiene
                
                //Llamo al metodo
                String solucion = this.modelo.leerArrayCaracteres();
                this.vista.escribirResultado(solucion);
                
                //Metodos para seguir con el programa
                this.vista.operacionExitosa();
                this.vista.limpiarCampos();
            }
            case InterfazVista.LEER_CARACTERES_BUFFERED_READER -> {
                //Recogida de parametros
                //no tiene
                
                //Llamo al metodo
                String solucion = this.modelo.leerCaracteresBufferReader();
                this.vista.escribirResultado(solucion);
                
                //Metodos para seguir con el programa
                this.vista.operacionExitosa();
                this.vista.limpiarCampos();
            }
            
        }
    }

}

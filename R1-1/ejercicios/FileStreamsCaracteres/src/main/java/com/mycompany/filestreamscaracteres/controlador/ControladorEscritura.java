/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.filestreamscaracteres.controlador;

import com.mycompany.filestreamscaracteres.modelo.Escritura;
import com.mycompany.filestreamscaracteres.vista.InterfazVista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author JHM by Jorge Herrera Martín
 * @version 1.0
 * created on 9 nov 2024
 */
public class ControladorEscritura implements ActionListener{
    
    private final InterfazVista vista;
    private final Escritura modelo;
    
    public ControladorEscritura (InterfazVista vistaInput, Escritura modeloInput){
        this.vista = vistaInput;
        this.modelo = modeloInput;
        this.vista.setControladorEscritura (this);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        switch(evento.getActionCommand()){
            case InterfazVista.ESCRIBIR_CARACTER  -> {
                char caracter = this.vista.leerCaracter();
                boolean add = this.vista.leerBoolean();

                this.modelo.escribirCaracter(caracter, add);

                this.vista.operacionExitosa();
                this.vista.limpiarCampos();
        
           }
            case InterfazVista.ESCRIBIR_ARRAY_CARACTERES ->{
                char[] listaCaracteres = this.vista.leerArrayCaracteres();
                boolean sobreescribe = this.vista.leerBoolean();
                
                this.modelo.escribirArrayCaracteres(listaCaracteres, sobreescribe);
                
                this.vista.operacionExitosa();
                this.vista.limpiarCampos();
                
            }
        }
        
    }

}

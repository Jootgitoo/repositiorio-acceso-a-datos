/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.cambioeuropesetaconmvc.controlador;

import com.mycompany.cambioeuropesetaconmvc.modelo.CambioEuroPeseta;
import com.mycompany.cambioeuropesetaconmvc.vista.InterfazVista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author JHM by Jorge Herrera Martín
 * @version 1.0
 * created on 9 nov 2024
 */
public class ControladorEuroPeseta implements ActionListener{
    
    private final InterfazVista vista;
    private final CambioEuroPeseta modelo;
    
    private double euros;
    private double pesetas;
    
    public ControladorEuroPeseta(InterfazVista vistaInput, CambioEuroPeseta modeloInput){
        this.vista = vistaInput;
        this.modelo = modeloInput;
        this.vista.setControladorCambioEuroPeseta (this);
    }

    
    @Override
    public void actionPerformed(ActionEvent evento) {
        
        switch (evento.getActionCommand()){
            
            case InterfazVista.CAMBIAR_DE_EUROS_A_PESETAS -> {
                this.euros = this.vista.getEuros();
                
                this.modelo.eurosToPeseta(this.euros);
                
                this.vista.operacionExitosa();
                
            }
            case InterfazVista.CAMBAIR_DE_PESETAS_A_EUROS -> {
                this.pesetas = this.vista.getPesetas();
                
                this.modelo.pesetaToEuro(this.pesetas);
                this.vista.operacionExitosa();
            }    
                
                
        }
        
    }

}
